package dev.android.kevin.project.presenter;

import android.util.Log;

import dev.android.kevin.project.Constant;
import dev.android.kevin.project.base.contract.DetailFragmentContract;
import dev.android.kevin.project.data.DataManager;
import dev.android.kevin.project.data.network.RetrofitManager;
import dev.android.kevin.project.data.prefs.SharePreferenceImpl;
import dev.android.kevin.project.model.DetailBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kevinsun on 2/22/18.
 */

public class DetailFragmentPresenter implements DetailFragmentContract.Presenter {

    private DetailFragmentContract.View view;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private DataManager dataManager;


    public DetailFragmentPresenter() {
        dataManager = new DataManager(RetrofitManager.provideUserRestService(), new SharePreferenceImpl());
    }


    @Override
    public void attachView(DetailFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void fetchDetail(String placeid) {
        DisposableObserver disposableObserver = dataManager.fetchDetail(placeid, Constant.BACKUP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showLoadingProgress();
                    }
                })
                .subscribeWith(new DisposableObserver<DetailBean>() {
                    @Override
                    public void onNext(DetailBean detailBean) {
                        view.showDetail(detailBean);
                        view.hideLoadingProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError===", e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        compositeDisposable.add(disposableObserver);
    }
}

package dev.android.kevin.project.presenter;

import dev.android.kevin.project.base.contract.DetailFragmentContract;
import dev.android.kevin.project.data.RetrofitManager;
import dev.android.kevin.project.model.DetailBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kevinsun on 2/22/18.
 */

public class DetailFragmentPresenter implements DetailFragmentContract.Presenter {

    private DetailFragmentContract.View view;


    @Override
    public void attachView(DetailFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void fetchDetail(String placeid) {
        RetrofitManager.provideUserRepository().fetchDetail(placeid, "AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<DetailBean>() {
                    @Override
                    public void onNext(DetailBean detailBean) {
                        view.showDetail(detailBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

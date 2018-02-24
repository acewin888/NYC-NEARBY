package dev.android.kevin.project.presenter;

import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dev.android.kevin.project.base.contract.ListFragmentContract;
import dev.android.kevin.project.data.DataManager;
import dev.android.kevin.project.data.network.RetrofitManager;
import dev.android.kevin.project.data.prefs.SharePreferenceImpl;
import dev.android.kevin.project.model.PlaceSearchBean;
import dev.android.kevin.project.util.Utility;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kevinsun on 2/22/18.
 */

public class ListFragmentPresenter implements ListFragmentContract.Presenter {

    private ListFragmentContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private DataManager dataManager;

    public ListFragmentPresenter() {
        dataManager = new DataManager(RetrofitManager.provideUserRestService(), new SharePreferenceImpl());
    }

    @Override
    public void attachView(ListFragmentContract.View view) {
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
    public void fetchList(String keyword) {

        if(dataManager.searchByDistance()){

            DisposableObserver disposableObserver = dataManager.fetchListByRank("40.736748369881035,-73.82068104165768", "distance", dataManager.getSearchType(), keyword, "AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            view.showLoadingProgress();
                        }
                    })
                    .subscribeWith(new DisposableObserver<PlaceSearchBean>() {

                        @Override
                        public void onNext(PlaceSearchBean bean) {

                            view.showList(bean.getResults());
                            view.hideLoadingProgress();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });


        }
        DisposableObserver disposableObserver = dataManager.fetchList("40.736748369881035,-73.82068104165768", dataManager.getRadius(), dataManager.getSearchType(), keyword, "AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showLoadingProgress();
                    }
                })
                .subscribeWith(new DisposableObserver<PlaceSearchBean>() {

                    @Override
                    public void onNext(PlaceSearchBean bean) {

                        view.showList(bean.getResults());
                        view.hideLoadingProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(disposableObserver);
    }
}



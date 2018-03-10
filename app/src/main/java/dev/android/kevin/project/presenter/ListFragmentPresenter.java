package dev.android.kevin.project.presenter;

import android.util.Log;

import java.util.List;

import dev.android.kevin.project.Constant;
import dev.android.kevin.project.base.contract.ListFragmentContract;
import dev.android.kevin.project.data.DataManager;
import dev.android.kevin.project.data.network.RetrofitManager;
import dev.android.kevin.project.data.prefs.SharePreferenceImpl;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kevinsun on 2/22/18.
 */

public class ListFragmentPresenter implements ListFragmentContract.Presenter {

    private ListFragmentContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private DataManager dataManager;

    public ListFragmentPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
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
    public void fetchList(String keyword, String location) {
        if (dataManager.isSearchByDistance()) {
            DisposableObserver disposableObserver = dataManager.fetchListByRank(location, Constant.SORT_BY_DISTANCE, dataManager.getSearchType(), keyword, Constant.BACKUP_KEY)
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
                            Log.d("xuyangonOnerror", e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            compositeDisposable.add(disposableObserver);


        } else {
            DisposableObserver disposableObserver = dataManager.fetchList(location, dataManager.getRadius(), dataManager.getSearchType(), keyword, Constant.BACKUP_KEY)
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
                            List<PlaceSearchBean.Results> list = bean.getResults();

                            if (list == null || list.size() == 0) {
                                view.showError("error");
                            } else {
                                view.showList(bean.getResults());
                                view.hideLoadingProgress();
                            }
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
}



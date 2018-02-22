package dev.android.kevin.project.presenter;

import android.util.Log;

import dev.android.kevin.project.base.contract.ListFragmentContract;
import dev.android.kevin.project.data.RetrofitManager;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kevinsun on 2/22/18.
 */

public class ListFragmentPresenter implements ListFragmentContract.Presenter {

    private ListFragmentContract.View view;

    @Override
    public void attachView(ListFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void fetchList(String keyword) {


                String search = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.736748369881035,-73.82068104165768&radius=5000&type=restaurant&keyword=chinese&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";

        RetrofitManager.provideUserRepository().fetchList("40.736748369881035,-73.82068104165768","5000",keyword, "chinese","AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20" )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<PlaceSearchBean>() {
                    @Override
                    public void onNext(PlaceSearchBean placeSearchBean) {
                       Log.d("xuyang=======", String.valueOf(placeSearchBean.getResults()));

                       view.showList(placeSearchBean.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("xuyang", e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

package dev.android.kevin.project.presenter;

import android.content.Context;
import android.location.LocationManager;
import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import dev.android.kevin.project.UI.MainActivity;
import dev.android.kevin.project.base.contract.MainActivityContract;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kevinsun on 2/22/18.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MainActivityContract.View view;


    @Override
    public void attachView(MainActivityContract.View view) {
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
    public void searchQuery(String keyword) {
        view.showSearchQuery(keyword);
    }

    @Override
    public void populateListFragment(String keyword, double currentLatitude, double currentLongitude) {
        view.showListFragment(keyword, currentLatitude, currentLongitude);
    }

    @Override
    public void populateDetailFragment(String placeid) {
        view.showDetailFragment(placeid);
    }
}

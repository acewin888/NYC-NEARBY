package dev.android.kevin.project.presenter;

import dev.android.kevin.project.UI.MainActivity;
import dev.android.kevin.project.base.contract.MainActivityContract;

/**
 * Created by kevinsun on 2/22/18.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;


    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void searchQuery(String keyword) {
        view.showSearchQuery(keyword);

    }

    @Override
    public void populateListFragment(String keyword) {
        view.showListFragment(keyword);

    }

    @Override
    public void populateDetailFragment(String placeid) {
        view.showDetailFragment(placeid);
    }
}

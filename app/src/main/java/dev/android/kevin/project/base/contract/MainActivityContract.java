package dev.android.kevin.project.base.contract;

import dev.android.kevin.project.base.BasePresenter;
import dev.android.kevin.project.base.BaseView;

/**
 * Created by kevinsun on 2/22/18.
 */

public interface MainActivityContract {


    interface Presenter extends BasePresenter<View> {

        void searchQuery(String keyword);


        void populateListFragment(String keyword, double currentLatitude, double currentLongitude);


        void populateDetailFragment(String placeid);

    }


    interface View extends BaseView {



        void showSearchQuery(String keyword);

        void showListFragment(String keyword, double currentLatitude, double currentLongitude);

        void showDetailFragment(String placeid);


    }
}

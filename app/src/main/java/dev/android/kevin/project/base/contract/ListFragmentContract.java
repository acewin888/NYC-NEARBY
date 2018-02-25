package dev.android.kevin.project.base.contract;

import java.util.List;

import dev.android.kevin.project.base.BasePresenter;
import dev.android.kevin.project.base.BaseView;
import dev.android.kevin.project.model.PlaceSearchBean;

/**
 * Created by kevinsun on 2/22/18.
 */

public interface ListFragmentContract {

    interface Presenter extends BasePresenter<View>{

        void fetchList(String keyword, String location);
    }


    interface View extends BaseView{


        void showList(List<PlaceSearchBean.Results> list);

        void hideLoadingProgress();

        void showError(String errorMessage);

        void showLoadingProgress();

    }
}

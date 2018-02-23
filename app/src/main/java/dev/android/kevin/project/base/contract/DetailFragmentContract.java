package dev.android.kevin.project.base.contract;

import dev.android.kevin.project.base.BasePresenter;
import dev.android.kevin.project.base.BaseView;
import dev.android.kevin.project.model.DetailBean;

/**
 * Created by kevinsun on 2/22/18.
 */

public interface DetailFragmentContract {


    interface Presenter extends BasePresenter<View>{

        void fetchDetail(String placeid);
    }


    interface View extends BaseView{

        void showDetail(DetailBean detailBean);

    }
}

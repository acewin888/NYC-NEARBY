package dev.android.kevin.project.base;

/**
 * Created by kevinsun on 2/22/18.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void removeView();

}

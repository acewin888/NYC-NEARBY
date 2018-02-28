package dev.android.kevin.project;

import dev.android.kevin.project.model.DetailBean;
import io.reactivex.Observable;

/**
 * Created by kevinsun on 2/26/18.
 */

public interface MockDatamanger {

    public Observable<DetailBean> fetchDetail(String placeid, String key);
}

package dev.android.kevin.project.data;

import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.Observable;

/**
 * Created by kevinsun on 2/21/18.
 */

public interface Repository {

    Observable<PlaceSearchBean> fetchList(String location, String radius, String type, String keyword, String key);

    Observable<DetailBean> fetchDetail(String placeid, String key);



}

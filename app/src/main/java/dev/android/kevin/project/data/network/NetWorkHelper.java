package dev.android.kevin.project.data.network;

import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by kevinsun on 2/21/18.
 */

public interface NetWorkHelper {

    Observable<PlaceSearchBean> fetchList(String location, String radius, String type, String keyword, String key);

    Observable<DetailBean> fetchDetail(String placeid, String key);

    Observable<PlaceSearchBean> fetchListByRank(String location, String rankby, String type, String keyword, String key);



}

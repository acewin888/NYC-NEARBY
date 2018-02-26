package dev.android.kevin.project.data.network;

import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kevinsun on 2/21/18.
 */

public interface RetrofitAPI {


    @GET("nearbysearch/json")
    Observable<PlaceSearchBean> fetchList(@Query("location") String location, @Query("radius") String radius, @Query("type") String type, @Query("keyword") String keyword, @Query("key") String key);


    @GET("details/json")
    Observable<DetailBean> fetchDetail(@Query("placeid") String placeid, @Query("key") String key);


    @GET("nearbysearch/json")
    Observable<PlaceSearchBean> fetchListByRank(@Query("location") String location, @Query("rankby") String rankby, @Query("type") String type, @Query("keyword") String keyword, @Query("key") String key);

}

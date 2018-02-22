package dev.android.kevin.project.data;

import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kevinsun on 2/21/18.
 */

public interface RetrofitAPI {


    String baseUrl = "https://maps.googleapis.com/maps/api/place/";
    String search = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.736748369881035,-73.82068104165768&radius=5000&type=restaurant&keyword=chinese&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";

    @GET("nearbysearch/json")
    Observable<PlaceSearchBean> fetchList(@Query("location") String location, @Query("radius") String radius, @Query("type") String type, @Query("keyword") String keyword, @Query("key") String key);


   // String detail = "https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJVVVV-UtewokRZwx6c-W9aoY&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";

    @GET("details/json")
    Observable<DetailBean> fetchDetail(@Query("placeid") String placeid, @Query("key") String key);
}

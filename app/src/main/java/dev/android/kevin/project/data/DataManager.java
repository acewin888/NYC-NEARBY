package dev.android.kevin.project.data;

import dev.android.kevin.project.data.network.NetWorkHelper;
import dev.android.kevin.project.data.network.RetrofitAPI;
import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.Observable;

/**
 * Created by kevinsun on 2/21/18.
 */

public class RepositoryImpl implements NetWorkHelper {

    private RetrofitAPI retrofitAPI;

    public RepositoryImpl(RetrofitAPI retrofitAPI) {
        this.retrofitAPI = retrofitAPI;
    }


    @Override
    public Observable<PlaceSearchBean> fetchList(String location, String radius, String type, String keyword, String key) {
        return retrofitAPI.fetchList(location, radius, type, keyword, key);
    }

    @Override
    public Observable<DetailBean> fetchDetail(String placeid, String key) {
        return retrofitAPI.fetchDetail(placeid, key);
    }
}

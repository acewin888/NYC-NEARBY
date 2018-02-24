package dev.android.kevin.project.data;

import dev.android.kevin.project.data.network.NetWorkHelper;
import dev.android.kevin.project.data.network.RetrofitAPI;
import dev.android.kevin.project.data.prefs.SharePreferenceHelper;
import dev.android.kevin.project.data.prefs.SharePreferenceImpl;
import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.Observable;

/**
 * Created by kevinsun on 2/21/18.
 */

public class DataManager implements NetWorkHelper, SharePreferenceHelper {

    private RetrofitAPI retrofitAPI;

    private SharePreferenceImpl sharePreference;

    public DataManager(RetrofitAPI retrofitAPI, SharePreferenceImpl sharePreference) {
        this.retrofitAPI = retrofitAPI;
        this.sharePreference = sharePreference;
    }


    @Override
    public Observable<PlaceSearchBean> fetchList(String location, String radius, String type, String keyword, String key) {
        return retrofitAPI.fetchList(location, radius, type, keyword, key);
    }

    @Override
    public Observable<DetailBean> fetchDetail(String placeid, String key) {
        return retrofitAPI.fetchDetail(placeid, key);
    }

    @Override
    public Observable<PlaceSearchBean> fetchListByRank(String location, String rankby, String type, String keyword, String key) {
        return retrofitAPI.fetchListByRank(location, rankby, type, keyword, key);
    }

    @Override
    public String getRadius() {
        return sharePreference.getRadius();
    }

    @Override
    public String getSearchType() {
        return sharePreference.getSearchType();
    }

    @Override
    public boolean searchByRating() {
        return sharePreference.searchByRating();
    }

    @Override
    public boolean searchByDistance() {
        return sharePreference.searchByDistance();
    }
}

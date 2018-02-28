package dev.android.kevin.mock;

import dev.android.kevin.project.data.network.RetrofitAPI;
import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.Observable;

/**
 * Created by kevinsun on 2/26/18.
 */

public class MockApi implements RetrofitAPI {
    @Override
    public Observable<PlaceSearchBean> fetchList(String location, String radius, String type, String keyword, String key) {

        return null;
    }

    @Override
    public Observable<DetailBean> fetchDetail(String placeid, String key) {
        DetailBean detailBean = new DetailBean();
        return Observable.just(detailBean);
    }

    @Override
    public Observable<PlaceSearchBean> fetchListByRank(String location, String rankby, String type, String keyword, String key) {
        return null;
    }
}

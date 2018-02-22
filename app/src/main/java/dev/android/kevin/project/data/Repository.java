package dev.android.kevin.project.data;

import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.Observable;

/**
 * Created by kevinsun on 2/21/18.
 */

public interface Repository {

    Observable<PlaceSearchBean> fetchList();



}

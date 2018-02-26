package dev.android.kevin.project.data.prefs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dev.android.kevin.project.App;
import dev.android.kevin.project.Constant;

/**
 * Created by kevinsun on 2/24/18.
 */

public class SharePreferenceImpl implements SharePreferenceHelper {

    private SharedPreferences sharedPreferences;


    public SharePreferenceImpl() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

    @Override
    public String getRadius() {
        return sharedPreferences.getString(Constant.RADIUS_KEY, Constant.DEFAULT_RADIUS);
    }

    @Override
    public String getSearchType() {
        return sharedPreferences.getString(Constant.TYPE_LIST, Constant.DEFAULT_SEARCH_TYPE);
    }

    @Override
    public boolean searchByRating() {
        return sharedPreferences.getBoolean(Constant.RATING_KEY, Constant.DEFAULT_SEARCH_BY_RATING);
    }

    @Override
    public boolean isSearchByDistance() {
        return sharedPreferences.getBoolean(Constant.DISTANCE_KEY, Constant.DEFAULT_SEARCH_BY_DISTANCE);
    }
}

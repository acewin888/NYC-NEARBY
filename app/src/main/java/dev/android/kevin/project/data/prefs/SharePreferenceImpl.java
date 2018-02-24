package dev.android.kevin.project.model.prefs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dev.android.kevin.project.App;

/**
 * Created by kevinsun on 2/24/18.
 */

public class SharePreferenceImpl implements  SharePreferenceHelper {


    private  SharedPreferences sharedPreferences;


    private final String DEFAULT_RADIUS = "5000";
    private final String DEFAULT_SEARCH_TYPE = "restaurant";
    private final boolean DEFAULT_SEARCH_BY_RATING = false;
    private final boolean DEFAULT_SEARCH_BY_DISTANCE = false;


    public SharePreferenceImpl(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

    @Override
    public String getRadius() {
        return sharedPreferences.getString("radius_text", DEFAULT_RADIUS);
    }

    @Override
    public String getSearchType() {
        return sharedPreferences.getString("type_list", DEFAULT_SEARCH_TYPE);
    }

    @Override
    public boolean searchByRating() {
        return sharedPreferences.getBoolean("rating", DEFAULT_SEARCH_BY_RATING);
    }

    @Override
    public boolean searchByDistance() {
        return sharedPreferences.getBoolean("distance", DEFAULT_SEARCH_BY_DISTANCE);
    }
}

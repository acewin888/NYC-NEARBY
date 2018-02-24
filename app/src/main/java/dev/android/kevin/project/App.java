package dev.android.kevin.project;

import android.app.Application;

/**
 * Created by kevinsun on 2/24/18.
 */

public class App extends Application {

    private static App instance;


    public static synchronized App getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}

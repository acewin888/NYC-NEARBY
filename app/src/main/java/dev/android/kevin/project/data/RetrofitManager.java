package dev.android.kevin.project.data;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by kevinsun on 2/21/18.
 */

public class RetrofitManager {
    private static String BASE_API_URL = "https://maps.googleapis.com/maps/api/place/";
    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofitInstance;
    private static RetrofitAPI mUserRestService;

    public static void setBaseApiUrl(String url) {
        nullifyRetrofitInstance();
        BASE_API_URL = url;
    }

    public static Repository provideUserRepository() {
        return new RepositoryImpl(provideUserRestService());
    }

    private static void nullifyRetrofitInstance() {
        mUserRestService = null;
        mRetrofitInstance = null;
    }

    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        }
        return mOkHttpClient;
    }

    private static Retrofit getRetrofitInstance() {
        if (mRetrofitInstance == null) {
            mRetrofitInstance = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRetrofitInstance;
    }

    private static RetrofitAPI provideUserRestService() {
        if (mUserRestService == null) {
            mUserRestService = getRetrofitInstance().create(RetrofitAPI.class);
        }
        return mUserRestService;
    }
}

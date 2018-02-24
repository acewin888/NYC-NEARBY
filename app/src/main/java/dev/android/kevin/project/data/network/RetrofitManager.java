package dev.android.kevin.project.data;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
            synchronized (RetrofitManager.class) {
          //      Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                 HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

                /**
                 * this is for logging the request info need to set to only debug
                 */
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

                mOkHttpClient = new OkHttpClient.Builder()
                   //     .cache(cache)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .addNetworkInterceptor(httpLoggingInterceptor)
                        .build();
            }
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

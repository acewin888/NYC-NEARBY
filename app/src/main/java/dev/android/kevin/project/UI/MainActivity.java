package dev.android.kevin.project.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import dev.android.kevin.project.R;
import dev.android.kevin.project.data.RetrofitManager;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {



    TextView example;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        example = (TextView) findViewById(R.id.example);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        String search = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.736748369881035,-73.82068104165768&radius=5000&type=restaurant&keyword=chinese&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";

        RetrofitManager.provideUserRepository().fetchList("40.736748369881035,-73.82068104165768","5000","restaurant", "chinese","AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20" )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<PlaceSearchBean>() {
                    @Override
                    public void onNext(PlaceSearchBean placeSearchBean) {
                       Log.d("xuyang=======", String.valueOf(placeSearchBean.getResults()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("xuyang", e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}

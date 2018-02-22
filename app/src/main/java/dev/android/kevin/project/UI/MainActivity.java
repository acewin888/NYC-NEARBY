package dev.android.kevin.project.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

import dev.android.kevin.project.R;
import dev.android.kevin.project.base.contract.MainActivityContract;
import dev.android.kevin.project.data.RetrofitManager;
import dev.android.kevin.project.model.PlaceSearchBean;
import dev.android.kevin.project.presenter.MainActivityPresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {


    android.support.v7.widget.SearchView searchView;


    private MainActivityContract.Presenter presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter();

        presenter.attachView(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        searchView = (android.support.v7.widget.SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(onQueryTextListener);



    }

    @Override
    public void hideLoadingProgress() {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showLoadingProgress() {

    }

    @Override
    public void showSearchQuery(String keyword) {
        presenter.populateListFragment(keyword);

    }

    @Override
    public void showListFragment(String keyword) {
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        listFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.response_container, listFragment,"listfragment");
        transaction.commit();


    }


    private android.support.v7.widget.SearchView.OnQueryTextListener onQueryTextListener = new android.support.v7.widget.SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Log.d("xuyang", query);

            presenter.searchQuery(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {

            Log.d("xuyang=== textchanged", newText);
            return false;
        }
    };


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


    //        String search = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.736748369881035,-73.82068104165768&radius=5000&type=restaurant&keyword=chinese&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";
//
//        RetrofitManager.provideUserRepository().fetchList("40.736748369881035,-73.82068104165768","5000","restaurant", "chinese","AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20" )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<PlaceSearchBean>() {
//                    @Override
//                    public void onNext(PlaceSearchBean placeSearchBean) {
//                       Log.d("xuyang=======", String.valueOf(placeSearchBean.getResults()));
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("xuyang", e.toString());
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
}

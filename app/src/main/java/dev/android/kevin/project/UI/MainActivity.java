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

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.android.kevin.project.R;
import dev.android.kevin.project.UI.adpater.PlaceSearchAdapter;
import dev.android.kevin.project.base.contract.MainActivityContract;
import dev.android.kevin.project.data.RetrofitManager;
import dev.android.kevin.project.model.PlaceSearchBean;
import dev.android.kevin.project.presenter.MainActivityPresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, PlaceSearchAdapter.OnItemClickListener{


    private MainActivityContract.Presenter presenter;


    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.searchView)
    android.support.v7.widget.SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new MainActivityPresenter();

        presenter.attachView(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        searchView.setOnQueryTextListener(onQueryTextListener);


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
        transaction.add(R.id.response_container, listFragment, "listfragment");
        transaction.commit();


    }

    @Override
    public void showDetailFragment(String placeid) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("placeid", placeid);
        detailFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.response_container, detailFragment, "detailfragment").addToBackStack("stack");
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

    @Override
    public void onItemClick(String placeid) {
        presenter.populateDetailFragment(placeid);
    }


}

package dev.android.kevin.project.UI;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.android.kevin.project.Constant;
import dev.android.kevin.project.R;
import dev.android.kevin.project.UI.adpater.PlaceSearchAdapter;
import dev.android.kevin.project.base.contract.MainActivityContract;
import dev.android.kevin.project.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, PlaceSearchAdapter.OnItemClickListener {


    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.searchView)
    android.support.v7.widget.SearchView searchView;

    private final int REQUEST_LOCATION = 5;
    private MainActivityContract.Presenter presenter;
    private double lattitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        presenter = new MainActivityPresenter();
        presenter.attachView(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        searchView.setOnQueryTextListener(onQueryTextListener);

        presenter.fetchCurrentLocation(this);

    }

    @OnClick(R.id.filter_setting)
    public void onClick() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSearchQuery(String keyword) {
        presenter.populateListFragment(keyword, lattitude, longitude);
    }

    @Override
    public void showListFragment(String keyword, double currentLatitude, double currentLongitude) {
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEYWORD_LISTFRAGMENT, keyword);
        bundle.putDouble(Constant.LATITUDE_LISTFRAGMENT, currentLatitude);
        bundle.putDouble(Constant.LONGITUDE_LISTFRAGMENT, currentLongitude);
        listFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.response_container, listFragment, Constant.LISTFRAGMENT_TAG);
        transaction.commit();


    }

    @Override
    public void showDetailFragment(String placeid) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.PLACEID, placeid);
        detailFragment.setArguments(bundle);
        bundle.putDouble(Constant.LATITUDE_DETAILFRAGMENT, lattitude);
        bundle.putDouble(Constant.LONGITUDE_DETAILFRAGMENT, longitude);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.response_container, detailFragment, Constant.DETAILFRAGMENT_TAG).addToBackStack(Constant.BACKSTACK);
        transaction.commit();

    }

    @Override
    public void setCurrentLocation(double lat, double lon) {
        lattitude = lat;
        longitude = lon;
    }

    @Override
    public void showLocationError() {
        Toast.makeText(this, "Unble to Trace your location", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTurnOnLocationAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    private android.support.v7.widget.SearchView.OnQueryTextListener onQueryTextListener = new android.support.v7.widget.SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Log.d("==onQueryTextSubmit", query);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}

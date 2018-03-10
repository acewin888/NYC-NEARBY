package dev.android.kevin.project.presenter;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.android.kevin.project.UI.MainActivity;
import dev.android.kevin.project.base.contract.MainActivityContract;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by kevinsun on 3/10/18.
 */
public class MainActivityPresenterTest {


    @Mock
    MainActivityContract.View view;

    @Mock
    Context context;


    MainActivityContract.Presenter presenter;


    private String searchQuery = "this is test";

    private String keyword = "keyword";

    private double lat = 1.11;
    private double lon = -11.433;


    private String detailFragment = "detail";





    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MainActivityPresenter();
        presenter.attachView(view);

    }



    @Test
    public void searchQuery() throws Exception {
        presenter.searchQuery(searchQuery);

        verify(view).showSearchQuery(searchQuery);
    }

    @Test
    public void populateListFragment() throws Exception {
        presenter.populateListFragment(keyword, lat, lon);
        verify(view).showListFragment(keyword, lat, lon);

    }

    @Test
    public void populateDetailFragment() throws Exception {
        presenter.populateDetailFragment(detailFragment);
        verify(view).showDetailFragment(detailFragment);
    }

    @Test
    public void fetchCurrentLocation() throws Exception {
        presenter.fetchCurrentLocation(context);
        verify(view).setCurrentLocation(lat, lon);
    }

}
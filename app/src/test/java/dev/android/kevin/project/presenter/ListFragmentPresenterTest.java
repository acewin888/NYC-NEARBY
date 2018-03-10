package dev.android.kevin.project.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dev.android.kevin.project.Constant;
import dev.android.kevin.project.base.contract.ListFragmentContract;
import dev.android.kevin.project.data.DataManager;
import dev.android.kevin.project.model.PlaceSearchBean;
import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by kevinsun on 3/10/18.
 */
public class ListFragmentPresenterTest {



    @Mock
    ListFragmentContract.View view;

    @Mock
    DataManager dataManager;


    @Mock


    private ListFragmentContract.Presenter presenter;


    PlaceSearchBean placeSearchBean = new PlaceSearchBean();



    String location = "loction";

    String keyword = "keyoword";


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new ListFragmentPresenter(dataManager);

    }

    @Test
    public void fetchList() throws Exception {

        Mockito.when(dataManager.isSearchByDistance()).thenReturn(true);
        Mockito.when(dataManager.getSearchType()).thenReturn("restaurant");
        Mockito.when(dataManager.fetchListByRank(location, Constant.SORT_BY_DISTANCE,keyword, dataManager.getSearchType(), Constant.BACKUP_KEY)).thenReturn(Observable.just(placeSearchBean));

        presenter.fetchList(keyword, location);
        verify(view).showList(placeSearchBean.getResults());


    }

}
package dev.android.kevin.project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import dev.android.kevin.mock.MockApi;
import dev.android.kevin.project.base.contract.DetailFragmentContract;
import dev.android.kevin.project.data.DataManager;
import dev.android.kevin.project.data.prefs.SharePreferenceHelper;
import dev.android.kevin.project.data.prefs.SharePreferenceImpl;
import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.presenter.DetailFragmentPresenter;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by kevinsun on 2/26/18.
 */

public class DetailFragmentPresenterTest {


    @Mock
    public DetailFragmentContract.View view;

    @Mock
    public MockApi mockApi;


    @Mock
    public SharePreferenceHelper sharePreference;


    private DetailFragmentPresenter presenter;


    private Scheduler testScheduler;

    private MockDatamanger dataManager;

    DetailBean detailBean;


    @Before
    public void setUp(){

        detailBean = new DetailBean();
        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();

        //dataManager = new DataManager(mockApi, sharePreference);

        presenter = new DetailFragmentPresenter(dataManager);
    }




    @Test
    public void fetchDetailTest(){



        doReturn(Observable.just(detailBean)).when(dataManager).fetchDetail("xu", "cd");

        presenter.fetchDetail("xuyang");
        verify(view).showDetail(detailBean);
    }
}

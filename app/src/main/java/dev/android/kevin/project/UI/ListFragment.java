package dev.android.kevin.project.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.android.kevin.project.R;
import dev.android.kevin.project.UI.adpater.PlaceSearchAdapter;
import dev.android.kevin.project.base.contract.ListFragmentContract;
import dev.android.kevin.project.model.PlaceSearchBean;
import dev.android.kevin.project.presenter.ListFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListFragmentContract.View{



    public ListFragment() {
        // Required empty public constructor
    }


    private ListFragmentContract.Presenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        ButterKnife.bind(this,view);


        presenter = new ListFragmentPresenter();

        presenter.attachView(this);


        presenter.fetchList(getArguments().getString("keyword"));

        return  view;
    }

    @Override
    public void showList(List<PlaceSearchBean.Results> list) {
        PlaceSearchAdapter adapter = new PlaceSearchAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void hideLoadingProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showLoadingProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
}

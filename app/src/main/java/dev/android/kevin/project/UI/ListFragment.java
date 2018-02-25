package dev.android.kevin.project.UI;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import dev.android.kevin.project.util.ListItemDecoration;


public class ListFragment extends Fragment implements ListFragmentContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private double currentLatitude;
    private double currentLongitude;

    private ListFragmentContract.Presenter presenter;
    private ListItemDecoration listItemDecoration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        currentLatitude = getArguments().getDouble("lat");
        currentLongitude = getArguments().getDouble("long");
        listItemDecoration = new ListItemDecoration(getActivity());
        presenter = new ListFragmentPresenter();
        presenter.attachView(this);
        presenter.fetchList(getArguments().getString("keyword"), currentLatitude + "," + currentLongitude);
    }

    @Override
    public void showList(List<PlaceSearchBean.Results> list) {
        PlaceSearchAdapter adapter = new PlaceSearchAdapter(list, getActivity());
        adapter.setCurrentlLocation(currentLatitude, currentLongitude);
        adapter.setOnItemClickListener((MainActivity) getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(listItemDecoration);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.removeView();
    }
}

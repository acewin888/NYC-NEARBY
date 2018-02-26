package dev.android.kevin.project.UI;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.android.kevin.project.Constant;
import dev.android.kevin.project.R;
import dev.android.kevin.project.UI.adpater.PhotoAdapter;
import dev.android.kevin.project.UI.adpater.ReviewAdapter;
import dev.android.kevin.project.base.contract.DetailFragmentContract;
import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.presenter.DetailFragmentPresenter;
import dev.android.kevin.project.util.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements DetailFragmentContract.View {


    @BindView(R.id.item_icon)
    ImageView iconView;
    @BindView(R.id.item_name)
    TextView nameView;
    @BindView(R.id.item_rating)
    TextView ratingView;
    @BindView(R.id.item_types)
    TextView typesView;
    @BindView(R.id.item_address)
    TextView addressView;
    @BindView(R.id.item_distance)
    TextView distanceView;
    @BindView(R.id.item_price)
    TextView priceView;
    @BindView(R.id.photo_list)
    RecyclerView photo_recyclerView;
    @BindView(R.id.review_list)
    RecyclerView review_recyclerView;
    @BindView(R.id.item_phone_number)
    TextView phoneView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.detail_container)
    LinearLayout containerView;

    private double currentLat;
    private double currentLong;
    private DetailFragmentContract.Presenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        currentLat = getArguments().getDouble(Constant.LATITUDE_DETAILFRAGMENT);
        currentLong = getArguments().getDouble(Constant.LONGITUDE_DETAILFRAGMENT);
        presenter = new DetailFragmentPresenter();
        presenter.attachView(this);

        String placeid = getArguments().getString(Constant.PLACEID);
        presenter.fetchDetail(placeid);
    }


    @Override
    public void showLoadingProgress() {
        containerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        containerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showDetail(DetailBean detailBean) {
        bindDetail(detailBean);
        bindPhotoRecyclerView(detailBean);
        bindReview_recyclerView(detailBean);
    }


    private void bindDetail(DetailBean detailBean) {
        Picasso.with(getActivity()).load(detailBean.getResult().getIcon()).into(iconView);
        nameView.setText(detailBean.getResult().getName());
        ratingView.setText(String.valueOf(detailBean.getResult().getRating()));
        addressView.setText(detailBean.getResult().getFormatted_address());
        phoneView.setText(detailBean.getResult().getFormatted_phone_number());
        priceView.setText(Utility.getPriceRange(detailBean.getResult().getPrice_level()));
        distanceView.setText(distance(currentLat, currentLong, detailBean));
        typesView.setText(getTypeString(detailBean));


    }

    private String getTypeString(DetailBean detailBean) {
        List<String> list = detailBean.getResult().getTypes();
        String words = "";
        for (int i = 0; i < list.size() - 1; i++) {
            words += list.get(i) + ", ";
        }
        words = words + list.get(list.size() - 1);

        return words;

    }

    private String distance(double currentlat, double currentLong, DetailBean detailBean) {
        double distance = Utility.distance(currentlat, detailBean.getResult().getGeometry().getLocation().getLat(),
                currentLong, detailBean.getResult().getGeometry().getLocation().getLng(), 0, 0);

        return String.valueOf((int) distance);
    }

    private void bindPhotoRecyclerView(DetailBean detailBean) {
        PhotoAdapter photoAdapter = new PhotoAdapter(detailBean.getResult().getPhotos(), getActivity());
        photo_recyclerView.setAdapter(photoAdapter);
        photo_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void bindReview_recyclerView(DetailBean detailBean) {
        ReviewAdapter reviewAdapter = new ReviewAdapter(detailBean.getResult().getReviews(), getActivity());
        review_recyclerView.setAdapter(reviewAdapter);
        review_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}

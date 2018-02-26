package dev.android.kevin.project.UI.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.android.kevin.project.Constant;
import dev.android.kevin.project.R;
import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.model.PlaceSearchBean;
import dev.android.kevin.project.util.Utility;

/**
 * Created by kevinsun on 2/21/18.
 */

public class PlaceSearchAdapter extends RecyclerView.Adapter<PlaceSearchAdapter.PlaceSearchViewHolder> {

    private List<PlaceSearchBean.Results> list;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private LayoutInflater layoutInflater;

    private double currentLat;
    private double currentLong;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PlaceSearchAdapter(List<PlaceSearchBean.Results> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setCurrentlLocation(double latitude, double longitudi) {
        currentLat = latitude;
        currentLong = longitudi;

    }

    @Override
    public PlaceSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlaceSearchViewHolder(layoutInflater.inflate(R.layout.search_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PlaceSearchViewHolder holder, final int position) {
        holder.nameView.setText(list.get(position).getName());
        holder.ratingView.setText(String.valueOf(list.get(position).getRating()));
        holder.addressView.setText(list.get(position).getVicinity());
        holder.typesView.setText(Utility.getTypeString(list.get(position).getTypes()));
        holder.priceView.setText(Utility.getPriceRange(list.get(position).getPrice_level()));
        holder.distanceView.setText(getDistance(list, position));

        if (list.get(position).getOpening_hours() != null) {
            if (!list.get(position).getOpening_hours().isOpen_now()) {
                holder.orderButton.setVisibility(View.GONE);
            }
        }

        List<PlaceSearchBean.Results.Photos> photos = list.get(position).getPhotos();
        if (photos != null) {
            String reference = photos.get(0).getPhoto_reference();
            //  String url1 = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=150&photoreference=" + reference + "&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";
            String url = Constant.PHOTO_URL_150 + reference + Constant.BACKUP_KEY_PICASSO;
            Picasso.with(context).load(url).into(holder.itemImage);
        }
        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeid = list.get(position).getPlace_id();
                onItemClickListener.onItemClick(placeid);
            }
        });

    }

    private String getDistance(List<PlaceSearchBean.Results> list, int position) {
        double lat2 = list.get(position).getGeometry().getLocation().getLat();
        double long2 = list.get(position).getGeometry().getLocation().getLng();
        double distanceWithCurrentLocation = Utility.distance(currentLat, lat2, currentLong, long2, 0, 0);
        int distance = (int) distanceWithCurrentLocation;
        return String.valueOf(distance);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PlaceSearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image)
        ImageView itemImage;
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
        @BindView(R.id.item_button)
        Button orderButton;
        @BindView(R.id.item_container)
        LinearLayout itemContainer;


        public PlaceSearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String placeid);
    }
}

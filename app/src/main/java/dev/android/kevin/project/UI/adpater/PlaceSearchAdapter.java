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
import dev.android.kevin.project.R;
import dev.android.kevin.project.model.DetailBean;
import dev.android.kevin.project.model.PlaceSearchBean;

/**
 * Created by kevinsun on 2/21/18.
 */

public class PlaceSearchAdapter extends RecyclerView.Adapter<PlaceSearchAdapter.PlaceSearchViewHolder> {

    private List<PlaceSearchBean.Results> list;

    private Context context;


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PlaceSearchAdapter(List<PlaceSearchBean.Results> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public PlaceSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false);

        return new PlaceSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceSearchViewHolder holder, final int position) {
        holder.nameView.setText(list.get(position).getName());
        holder.ratingView.setText(String.valueOf(list.get(position).getRating()));
        holder.addressView.setText(list.get(position).getVicinity());


        List<String> types = list.get(position).getTypes();


        String typeList = "";

        for (int i = 0; i < types.size(); i++) {
            typeList += types.get(i);
        }
        holder.typesView.setText(typeList);

        if (list.get(position).getOpening_hours() != null) {
            if (!list.get(position).getOpening_hours().isOpen_now()) {
                holder.orderButton.setVisibility(View.GONE);
            }
        }


        List<PlaceSearchBean.Results.Photos> photos = list.get(position).getPhotos();

        if (photos != null) {
            String reference = photos.get(0).getPhoto_reference();
            String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=150&photoreference=" + reference + "&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";
            Picasso.with(context).load(url).into(holder.itemImage);

        }

        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeid = list.get(position).getPlace_id();

                onItemClickListener.onItemClick(placeid);
            }
        });


        double lat1 = 40.736748369881035;
        double lon1 = -73.8206810416576;

        double lat2 = list.get(position).getGeometry().getLocation().getLat();
        double long2 = list.get(position).getGeometry().getLocation().getLng();

        double distance1 = distance(lat1, lat2, lon1, long2, 0, 0);
        int distance = (int) distance1;
        holder.distanceView.setText(String.valueOf(distance));


        holder.priceView.setText(getPriceRange(list.get(position).getPrice_level()));


    }

    private String getPriceRange(int rating) {
        String price;
        switch (rating) {
            case 0:
                price = "$";
                break;
            case 1:
                price = "$$";
                break;
            case 2:
                price = "$$$";
                break;
            case 3:
                price = "$$$$";
                break;
            case 4:
                price = "$$$$$";
                break;
            default:
                price = "unknow";

        }
        return price;
    }

    private double distance(double lat1, double lat2, double lon1,
                            double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
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

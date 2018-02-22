package dev.android.kevin.project.UI.adpater;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

    public PlaceSearchAdapter(List<PlaceSearchBean.Results> list) {
        this.list = list;
    }

    @Override
    public PlaceSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);

        return new PlaceSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceSearchViewHolder holder, int position) {
        holder.nameView.setText(list.get(position).getName());
        holder.ratingView.setText(String.valueOf(list.get(position).getRating()));
        holder.typesView.setText(String.valueOf(list.get(position).getTypes().toArray()));
        holder.addressView.setText(list.get(position).getVicinity());
        holder.priceView.setText(String.valueOf(list.get(position).getPrice_level()));

        if(list.get(position).getOpening_hours() != null ){
            if(list.get(position).getOpening_hours().isOpen_now()){
                holder.orderButton.setVisibility(View.GONE);
            }
        }



        List<PlaceSearchBean.Results.Photos> photos = list.get(position).getPhotos();

        if(photos != null){
            String reference = photos.get(0).getPhoto_reference();
            String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=500&photoreference=" + reference + "&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";
             Picasso.with(holder.itemImage.getContext()).load(url).into(holder.itemImage);

        }


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


        public PlaceSearchViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


        }
    }
}

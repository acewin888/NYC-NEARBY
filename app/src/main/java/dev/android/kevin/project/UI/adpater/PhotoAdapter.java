package dev.android.kevin.project.UI.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.android.kevin.project.R;
import dev.android.kevin.project.model.DetailBean;

/**
 * Created by kevinsun on 2/23/18.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {


    private List<DetailBean.Result.Photos> list;

    public PhotoAdapter(List<DetailBean.Result.Photos> list) {
        this.list = list;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);


        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

        String reference = list.get(position).getPhoto_reference();
        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=300&photoreference=" + reference + "&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";


        Picasso.with(holder.imageView.getContext()).load(url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
         if(list == null){
             return  0;
         }else {
             return  list.size();
         }
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.photo_imageView)
        ImageView imageView;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}

package dev.android.kevin.project.UI.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.android.kevin.project.Constant;
import dev.android.kevin.project.R;
import dev.android.kevin.project.model.DetailBean;

/**
 * Created by kevinsun on 2/23/18.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {


    private List<DetailBean.Result.Photos> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public PhotoAdapter(List<DetailBean.Result.Photos> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(layoutInflater.inflate(R.layout.photo_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        String reference = list.get(position).getPhoto_reference();
     //   String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=300&photoreference=" + reference + "&key=AIzaSyBBt4YtyVgJ2N3S7vUHlGw8F1sZY26bM20";
        String url = Constant.PHOTO_URL_300 + reference + Constant.BACKUP_KEY_PICASSO;

        Picasso.with(context).load(url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo_imageView)
        ImageView imageView;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

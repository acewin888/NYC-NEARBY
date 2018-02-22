package dev.android.kevin.project.UI.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dev.android.kevin.project.R;
import dev.android.kevin.project.model.PlaceSearchBean;

/**
 * Created by kevinsun on 2/21/18.
 */

public class PlaceSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PlaceSearchBean.Results> list;

    public PlaceSearchAdapter(List<PlaceSearchBean.Results> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);

        return new PlaceSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PlaceSearchViewHolder) holder).itemname.setText(list.get(position).getName());
        ((PlaceSearchViewHolder) holder).itemvicinity.setText(list.get(position).getVicinity());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PlaceSearchViewHolder extends RecyclerView.ViewHolder {


        TextView itemname, itemvicinity;

        public PlaceSearchViewHolder(View itemView) {
            super(itemView);


            itemname = (TextView) itemView.findViewById(R.id.item_name);
            itemvicinity = (TextView) itemView.findViewById(R.id.item_vicinity);
        }
    }
}

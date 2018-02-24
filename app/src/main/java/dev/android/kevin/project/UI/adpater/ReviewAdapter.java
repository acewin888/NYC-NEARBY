package dev.android.kevin.project.UI.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.android.kevin.project.R;
import dev.android.kevin.project.model.DetailBean;

/**
 * Created by kevinsun on 2/23/18.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<DetailBean.Result.Reviews> list;

    public ReviewAdapter(List<DetailBean.Result.Reviews> list) {
        this.list = list;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item, parent, false);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.authorView.setText(list.get(position).getAuthor_name());
        holder.ratingView.setText(String.valueOf(list.get(position).getRating()));
        holder.reviewView.setText(list.get(position).getText());
        holder.timeView.setText(list.get(position).getRelative_time_description());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.author)
        TextView authorView;
        @BindView(R.id.rating)
        TextView ratingView;
        @BindView(R.id.review_text)
        TextView reviewView;
        @BindView(R.id.review_time)
        TextView timeView;

        public ReviewViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}

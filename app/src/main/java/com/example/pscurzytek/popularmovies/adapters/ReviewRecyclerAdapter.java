package com.example.pscurzytek.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ViewHolder> {

    private final Context context;
    private List<Review> reviews = new ArrayList<>();

    public ReviewRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ReviewRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_review, parent, false);

        return new ReviewRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewRecyclerAdapter.ViewHolder holder, int position) {
        List<Review> reviews = this.reviews;

        if (reviews.size() - 1 >= position) {
            Review review = reviews.get(position);

            holder.reviewAuthor.setText(review.getAuthor());
        }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void swapData(List<Review> data) {
        if (data == null)
            reviews = new ArrayList<>();
        else {
            reviews = data;
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView reviewAuthor;

        ViewHolder(View itemView) {
            super(itemView);

            reviewAuthor = itemView.findViewById(R.id.author_tv);
        }
    }
}

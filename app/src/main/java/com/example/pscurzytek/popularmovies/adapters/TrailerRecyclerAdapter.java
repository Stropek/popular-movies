package com.example.pscurzytek.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.models.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerRecyclerAdapter extends RecyclerView.Adapter<TrailerRecyclerAdapter.ViewHolder> {

    private final Context context;
    private List<Trailer> trailers = new ArrayList<>();

    public TrailerRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_trailer, parent, false);

        return new TrailerRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<Trailer> trailers = this.trailers;

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public void swapData(List<Trailer> data) {
        if (data == null)
            trailers = new ArrayList<>();
        else {
            trailers = data;
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

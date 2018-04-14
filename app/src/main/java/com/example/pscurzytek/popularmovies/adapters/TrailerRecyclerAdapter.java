package com.example.pscurzytek.popularmovies.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        if (trailers.size() - 1 >= position) {
            final Trailer trailer = trailers.get(position);

            holder.trailerName.setText(trailer.getName());
            holder.playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = trailer.getKey();

                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + id));
                    try {
                        context.startActivity(appIntent);
                    } catch (ActivityNotFoundException ex) {
                        context.startActivity(webIntent);
                    }
                }
            });
        }
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
        TextView trailerName;
        ImageView playButton;

        ViewHolder(View itemView) {
            super(itemView);

            trailerName = itemView.findViewById(R.id.trailer_name_tv);
            playButton = itemView.findViewById(R.id.play_ib);
        }
    }
}

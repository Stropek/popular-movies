package com.example.pscurzytek.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Context context = getContext();

        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);
        }

        if (movie != null) {
            ImageView image = convertView.findViewById(R.id.movie_iv);

            Picasso.with(context).load(movie.getFullPosterPath()).into(image);
            image.setContentDescription(movie.getTitle());
        }

        return convertView;
    }
}

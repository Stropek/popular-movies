package com.example.pscurzytek.popularmovies.activities;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.data.MovieContract;
import com.example.pscurzytek.popularmovies.models.Movie;

public class OnClickHandlers {

    public static void onReviewClicked(View view) {
        TextView content = view.findViewById(R.id.content_tv);
        if (content.getMaxLines() == 2) {
            content.setMaxLines(Integer.MAX_VALUE);
        } else {
            content.setMaxLines(2);
        }
    }

    public static void onFavoriteClicked(View view, Resources resources, Movie movie) {
        ImageView favoriteStar = (ImageView) view;
        String tag = (String) favoriteStar.getTag();
        ContentResolver contentResolver = view.getContext().getContentResolver();

        if (tag.equals("favorite")) {
            Uri deleteUri = MovieContract.MovieEntry.CONTENT_URI.buildUpon().appendPath(movie.getIdAsString()).build();
            contentResolver.delete(deleteUri, null, null);

            favoriteStar.setTag("notFavorite");
            favoriteStar.setImageDrawable(resources.getDrawable(android.R.drawable.btn_star_big_off));
        } else {
            Uri createUri = MovieContract.MovieEntry.CONTENT_URI;
            contentResolver.insert(createUri, movie.toContentValues());

            favoriteStar.setTag("favorite");
            favoriteStar.setImageDrawable(resources.getDrawable(android.R.drawable.btn_star_big_on));
        }
    }
}

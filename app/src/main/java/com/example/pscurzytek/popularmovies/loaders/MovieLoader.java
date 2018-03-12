package com.example.pscurzytek.popularmovies.loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.pscurzytek.popularmovies.enums.SortOrder;
import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.services.MovieService;

import java.util.ArrayList;
import java.util.List;

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {
    private static String TAG = MovieLoader.class.getName();

    private List<Movie> movies = null;
    private MovieService movieService;
    private SortOrder sortOrder;

    public MovieLoader(@NonNull Context context, MovieService movieService, SortOrder sortOrder) {
        super(context);
        this.movieService = movieService;
        this.sortOrder = sortOrder;
    }

    @Nullable
    @Override
    public List<Movie> loadInBackground() {
        try {
            switch (sortOrder) {
                case MostPopular:
                    movies = movieService.getPopular(null);
                    break;
                case TopRated:
                    movies = movieService.getTopRated(null);
                    break;
            }

            return movies;
        }
        catch (Exception ex) {
            Log.e(TAG, "Failed to load movies");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void onStartLoading() {
        if (movies == null) {
            forceLoad();
        }
        else {
            deliverResult(movies);
        }
    }

    @Override
    public void deliverResult(List<Movie> data) {
        movies = data;
        super.deliverResult(data);
    }
}

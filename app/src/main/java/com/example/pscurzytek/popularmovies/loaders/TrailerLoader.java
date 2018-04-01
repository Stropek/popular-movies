package com.example.pscurzytek.popularmovies.loaders;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.pscurzytek.popularmovies.models.Trailer;
import com.example.pscurzytek.popularmovies.services.MovieService;

import java.util.List;

public class TrailerLoader extends AsyncTaskLoader<List<Trailer>> {
    private static String TAG = TrailerLoader.class.getName();

    private final int movieId;
    private final MovieService movieService;

    private List<Trailer> trailers = null;

    public TrailerLoader(Context context, MovieService movieService, int movieId) {
        super(context);
        this.movieService = movieService;
        this.movieId = movieId;
    }

    @Override
    public List<Trailer> loadInBackground() {
        try {
            return movieService.getTrailers(movieId);
        }
        catch (Exception ex) {
            Log.e(TAG, "Failed to load movie trailers");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void onStartLoading() {
        if (trailers == null) {
            forceLoad();
        } else {
            deliverResult(trailers);
        }
    }

    @Override
    public void deliverResult(List<Trailer> data) {
        trailers = data;
        super.deliverResult(data);
    }
}

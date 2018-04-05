package com.example.pscurzytek.popularmovies.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.pscurzytek.popularmovies.models.Review;
import com.example.pscurzytek.popularmovies.services.MovieService;

import java.util.List;

public class ReviewLoader extends AsyncTaskLoader<List<Review>> {
    private static String TAG = ReviewLoader.class.getName();

    private final int movieId;
    private final MovieService movieService;

    private List<Review> reviews = null;

    public ReviewLoader(Context context, MovieService movieService, int movieId) {
        super(context);
        this.movieService = movieService;
        this.movieId = movieId;
    }

    @Override
    public List<Review> loadInBackground() {
        try {
            return movieService.getReviews(movieId, null);
        }
        catch (Exception ex) {
            Log.e(TAG, "Failed to load movie reviews");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void onStartLoading() {
        if (reviews == null) {
            forceLoad();
        } else {
            deliverResult(reviews);
        }
    }

    @Override
    public void deliverResult(List<Review> data) {
        reviews = data;
        super.deliverResult(data);
    }
}

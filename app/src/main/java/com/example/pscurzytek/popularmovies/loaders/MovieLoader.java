package com.example.pscurzytek.popularmovies.loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.pscurzytek.popularmovies.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {
    private static String TAG = MovieLoader.class.getName();

    private List<Movie> movies = null;

    public MovieLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<Movie> loadInBackground() {
        try {
            movies = new ArrayList<>();

            movies.add(new Movie(1, "test", 10, 5.0, 10, "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
                    "en", "test", false, "", false, "overview", "", null));
            movies.add(new Movie(2, "test 2", 10, 5.0, 10, "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
                    "en", "test 2", false, "", false, "overview", "", null));
            movies.add(new Movie(3, "test", 30, 5.0, 30, "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
                    "en", "test", false, "", false, "overview", "", null));
            movies.add(new Movie(4, "test 4", 10, 5.0, 10, "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
                    "en", "test 4", false, "", false, "overview", "", null));

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

package com.example.pscurzytek.popularmovies.services;

import android.database.Cursor;

import com.example.pscurzytek.popularmovies.BuildConfig;
import com.example.pscurzytek.popularmovies.data.MovieContract;
import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.models.Review;
import com.example.pscurzytek.popularmovies.models.Trailer;
import com.example.pscurzytek.popularmovies.utils.JsonConverter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import khttp.KHttp;

public class MovieService {
    private final String BaseUrl = BuildConfig.MOVIE_DATABASE_BASE_URL;
    private final String ApiKey = BuildConfig.MOVIE_DATABASE_API_KEY;

    public List<Movie> getPopular(Integer page) {
        page = page == null ? 1 : page;
        String url = String.format("%s/popular?api_key=%s&language=en-US&page=%s", BaseUrl, ApiKey, page);

        JSONObject jsonObject = KHttp.get(url).getJsonObject();

        return JsonConverter.convertListTo(jsonObject, Movie.class);
    }

    public List<Movie> getTopRated(Integer page) {
        page = page == null ? 1 : page;
        String url = String.format("%s/top_rated?api_key=%s&language=en-US&page=%s", BaseUrl, ApiKey, page);

        JSONObject jsonObject = KHttp.get(url).getJsonObject();

        return JsonConverter.convertListTo(jsonObject, Movie.class);
    }

    public List<Movie> getFavorites(Cursor favorites) {
        List<Movie> movies = new ArrayList<>();

        if (favorites.getCount() == 0) {
            return movies;
        }

        favorites.moveToFirst();
        do {
            int favoriteId = favorites.getInt(favorites.getColumnIndex(MovieContract.MovieEntry._ID));

            String url = String.format("%s/%s?api_key=%s&language=en-US", BaseUrl, favoriteId, ApiKey);
            JSONObject jsonObject = KHttp.get(url).getJsonObject();

            Movie favoriteMovie = JsonConverter.convertTo(jsonObject, Movie.class);
            if (favoriteMovie != null) {
                movies.add(favoriteMovie);
            }
        }
        while (favorites.moveToNext());

        return movies;
    }

    public List<Review> getReviews(int movieId, Integer page) {
        page = page == null ? 1 : page;
        String url = String.format("%s/%s/reviews?api_key=%s&language=en-US&page=%s", BaseUrl, movieId, ApiKey, page);

        JSONObject jsonObject = KHttp.get(url).getJsonObject();

        return JsonConverter.convertListTo(jsonObject, Review.class);
    }

    public List<Trailer> getTrailers(int movieId) {
        String url = String.format("%s/%s/videos?api_key=%s&language=en-US", BaseUrl, movieId, ApiKey);

        JSONObject jsonObject = KHttp.get(url).getJsonObject();

        return JsonConverter.convertListTo(jsonObject, Trailer.class);
    }

}

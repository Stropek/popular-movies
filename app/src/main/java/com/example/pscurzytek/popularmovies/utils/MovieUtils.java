package com.example.pscurzytek.popularmovies.utils;

import com.example.pscurzytek.popularmovies.models.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieUtils {
    public static Movie convertToMovie(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }

        Gson gson = new GsonBuilder().create();
        Movie movie = gson.fromJson(jsonObject.toString(), Movie.class);

        if (movie.getId() > 0) {
            return movie;
        }
        return null;
    }

    public static List<Movie> convertToMovies(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }

        JSONArray array = jsonObject.optJSONArray("results");
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Movie movie = convertToMovie(array.optJSONObject(i));
            if (movie != null) {
                movies.add(movie);
            }
        }
        return movies;
    }
}

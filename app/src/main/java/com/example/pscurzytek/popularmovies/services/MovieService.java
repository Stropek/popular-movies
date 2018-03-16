package com.example.pscurzytek.popularmovies.services;

import com.example.pscurzytek.popularmovies.BuildConfig;
import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.utils.MovieUtils;

import org.json.JSONObject;
import java.util.List;

import khttp.KHttp;

public class MovieService {
    private final String BaseUrl = BuildConfig.MOVIE_DATABASE_BASE_URL;
    private final String ApiKey = System.getenv("MOVIE_API_KEY");

    public List<Movie> getPopular(Integer page) {
        page = page == null ? 1 : page;
        String url = String.format("%s/popular?api_key=%s&language=en-US&page=%s", BaseUrl, ApiKey, page);

        JSONObject jsonObject = KHttp.get(url).getJsonObject();

        return MovieUtils.convertToMovies(jsonObject);
    }

    public List<Movie> getTopRated(Integer page) {
        page = page == null ? 1 : page;
        String url = String.format("%s/top_rated?api_key=%s&language=en-US&page=%s", BaseUrl, ApiKey, page);

        JSONObject jsonObject = KHttp.get(url).getJsonObject();

        return MovieUtils.convertToMovies(jsonObject);
    }
}

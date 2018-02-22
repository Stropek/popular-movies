package com.example.pscurzytek.popularmovies.services;

//import android.util.Log;

import android.util.Log;

import com.example.pscurzytek.popularmovies.BuildConfig;
import com.example.pscurzytek.popularmovies.models.Movie;

//import org.json.JSONObject;

import java.util.ArrayList;

//import khttp.KHttp;

public class MovieService {

    private final String BaseUrl = BuildConfig.MOVIE_DATABASE_BASE_URL;
    private final String ApiKey = BuildConfig.MOVIE_DATABASE_API_KEY;

    //    https://api.themoviedb.org/3/movie/popular?api_key{api_key}=&language=en-US&page=1
    public ArrayList<Movie> getPopular(Integer page) {
//        String url = String.format("%s/popular?api_key=%s&language=en-US&page=%s", BaseUrl, ApiKey, page);
        page = page == null ? 1 : page;

        ArrayList<Movie> popularMovies = new ArrayList<>();

//        JSONObject jsonObject = KHttp.get(url).getJsonObject();

//        Log.d(TAG, String.format("%s %s", page, jsonObject));

        return popularMovies;
    }

    //    https://api.themoviedb.org/3/movie/top_rated?api_key={api_key}&language=en-US&page=1
    public void getTopRated(Integer page) {
//        String url = String.format("%s/top_rated?api_key=%s&language=en-US&page=%s", BaseUrl, ApiKey, page);
        page = page == null ? 1 : page;

//        Log.d(TAG, String.format("%s %s", page, url));
    }

    private static final String TAG = MovieService.class.getName();
}

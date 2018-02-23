package com.example.pscurzytek.popularmovies.utils;

import com.example.pscurzytek.popularmovies.models.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieUtils {
    private static final String ID = "id";
    private static final String Title = "title";
    private static final String VoteCount = "vote_count";
    private static final String VoteAverage = "vote_average";
    private static final String Popularity = "popularity";
    private static final String PosterPath = "poster_path";
    private static final String OriginalLanguage = "original_language";
    private static final String OriginalTitle = "original_title";
    private static final String Video = "video";
    private static final String GenreIds = "genre_ids";
    private static final String BackdropPath = "backdrop_path";
    private static final String Adult = "adult";
    private static final String Overview = "overview";
    private static final String ReleaseDate = "release_date";

    public static Movie convertToMovie(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }

        int id = jsonObject.optInt(ID);
        if (id <= 0) {
            return null;
        }

        String title = jsonObject.optString(Title);
        int voteCount = jsonObject.optInt(VoteCount);
        double voteAverage = jsonObject.optDouble(VoteAverage);
        double popularity = jsonObject.optDouble(Popularity);
        String posterPath = jsonObject.optString(PosterPath);
        String originalLanguage = jsonObject.optString(OriginalLanguage);
        String originalTitle = jsonObject.optString(OriginalTitle);
        boolean hasVideo = jsonObject.optBoolean(Video);
        String backdropPath = jsonObject.optString(BackdropPath);
        boolean isAdult = jsonObject.optBoolean(Adult);
        String overview = jsonObject.optString(Overview);
        String releaseDate = jsonObject.optString(ReleaseDate);

        JSONArray genreIdsJsonArray = jsonObject.optJSONArray(GenreIds);
        List<Integer> genreIds = new ArrayList<>();
        for (int i = 0; i < genreIdsJsonArray.length(); i ++){
            int genreId = genreIdsJsonArray.optInt(i);
            if (genreId <= 0) {
                continue;
            }
            genreIds.add(genreId);
        }

        return new Movie(id, title, voteCount, voteAverage, popularity, posterPath, originalLanguage,
                originalTitle, hasVideo, backdropPath, isAdult, overview, releaseDate, genreIds);
    }

    public static List<Movie> convertToMovies(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }

        JSONArray array = jsonObject.optJSONArray("results");
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Movie movie = MovieUtils.convertToMovie(array.optJSONObject(i));
            if (movie != null) {
                movies.add(movie);
            }
        }
        return movies;
    }
}

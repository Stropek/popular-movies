package com.example.pscurzytek.popularmovies.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable, ObjectWithId {
    private static final String basePosterPath = "http://image.tmdb.org/t/p/w185";

    private final int id;
    private final String title;
    @SerializedName("vote_count")
    private final int voteCount;
    @SerializedName("vote_average")
    private final double voteAverage;
    private final double popularity;
    @SerializedName("poster_path")
    private final String posterPath;
    @SerializedName("original_language")
    private final String originalLanguage;
    @SerializedName("original_title")
    private final String originalTitle;
    @SerializedName("video")
    private final boolean hasVideo;
    @SerializedName("backdrop_path")
    private final String backdropPath;
    @SerializedName("adult")
    private final boolean isAdult;
    private final String overview;
    @SerializedName("release_date")
    private final String releaseDate;
    @SerializedName("genre_ids")
    private final List<Integer> genreIds;

    public Movie(int id, String title, int voteCount, double voteAverage, double popularity, String posterPath, String originalLanguage,
                 String originalTitle, boolean hasVideo, String backdropPath, boolean isAdult, String overview, String releaseDate, List<Integer> genreIds) {
        this.id = id;
        this.title = title;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.hasVideo = hasVideo;
        this.backdropPath = backdropPath;
        this.isAdult = isAdult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
    }

    public int getId() {
        return id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getFullPosterPath() {
        return String.format("%s%s", basePosterPath, posterPath);
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public boolean hasVideo() {
        return hasVideo;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    @Override
    public String getIdAsString() {
        return String.format("%s", id);
    }
}

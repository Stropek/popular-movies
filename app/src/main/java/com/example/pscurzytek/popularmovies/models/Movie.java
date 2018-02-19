package com.example.pscurzytek.popularmovies.models;

import java.util.List;

/**
 * Created by p.s.curzytek on 2/19/2018.
 */

public class Movie {
    private int id;
    private String title;
    private int voteCount;
    private double voteAverage;
    private double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private boolean hasVideo;
    private String backdropPath;
    private boolean isAdult;
    private String overview;
    private String releaseDate;
    private List<Integer> genreIds;

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
}

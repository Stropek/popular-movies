package com.example.pscurzytek.popularmovies;

import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.services.MovieService;

import org.junit.Test;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class MovieServiceTest {

    @Test
    public void useMockMovieApi() {
        if (BuildConfig.BUILD_TYPE.contentEquals("staging")) {
            assertEquals("http://localhost:5000/mockMovieApi/", BuildConfig.MOVIE_DATABASE_BASE_URL);
        } else {
            assertEquals("https://api.themoviedb.org/3/movie/", BuildConfig.MOVIE_DATABASE_BASE_URL);
        }
    }

    @Test
    public void getPopular_returnsListOfFilmsWithDescendingPopularity() {
        // given
        MovieService movieService = new MovieService();

        ArrayList<Movie> test = movieService.getPopular(1);
        // when

        int num = test.size();

        // then
    }

    @Test
    public void getPopular_pageProvided_returnsListOfFilmsWithDescendingPopularity() {
        // given

        // when

        // then
    }

    @Test
    public void getTopRated_returnsListOfFilmsWithDescendingRate() {
        // given

        // when

        // then
    }

    @Test
    public void getTopRated_pageProvided_returnsListOfFilmsWithDescendingRate() {
        // given

        // when

        // then
    }
}

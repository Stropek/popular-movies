package com.example.pscurzytek.popularmovies;

import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.models.Trailer;
import com.example.pscurzytek.popularmovies.utils.JsonConverter;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class JsonConverterTests {

    @Test
    public void convertToTrailer_nullJsonObject_returnsNull() throws JSONException {
        // when
        Trailer result = JsonConverter.convertTo(null, Trailer.class);

        // then
        assertEquals(null, result);
    }

    @Test
    public void convertToMovie_nullJsonObject_returnsNull() throws JSONException {
        // when
        Movie result = JsonConverter.convertTo(null, Movie.class);

        // then
        assertEquals(null, result);
    }

    @Test
    public void convertToMovie_validJsonObject_returnsMovie() throws JSONException {
        // given
        String json = MockResponses.SingleMovieResponse;
        JSONObject jsonObject = new JSONObject(json);

        // when
        Movie result = JsonConverter.convertTo(jsonObject, Movie.class);

        // then
        assertEquals(1, result.getId());
        assertEquals("movie title", result.getTitle());
        assertEquals(10, result.getVoteCount());
        assertEquals(10.5, result.getVoteAverage());
        assertEquals(15.8, result.getPopularity());
        assertEquals("/path.jpg", result.getPosterPath());
        assertEquals("http://image.tmdb.org/t/p/w185/path.jpg", result.getFullPosterPath());
        assertEquals("hi", result.getOriginalLanguage());
        assertEquals("title in spanish", result.getOriginalTitle());
        assertEquals(false, result.hasVideo());
        assertEquals("/backdrop.jpg", result.getBackdropPath());
        assertEquals(false, result.isAdult());
        assertEquals("movie overview", result.getOverview());
        assertEquals("1995-10-20", result.getReleaseDate());
        assertEquals(2, result.getGenreIds().get(0).intValue());
        assertEquals(4, result.getGenreIds().get(1).intValue());
    }

    @Test
    public void convertToMovies_nullJsonObject_returnsNull() throws JSONException {
        // when
        List<Movie> result = JsonConverter.convertListTo(null, Movie.class);

        // then
        assertEquals(null, result);
    }

    @Test
    public void convertToMovies_validJsonObject_returnsListOfMovies() throws JSONException {
        // given
        String json = MockResponses.MoviesPageResponse;
        JSONObject jsonObject = new JSONObject(json);

        // when
        List<Movie> result = JsonConverter.convertListTo(jsonObject, Movie.class);

        // then
        assertEquals(3, result.size());
        assertEquals(null, result.get(0).getTitle());
        assertEquals("movie title", result.get(1).getTitle());
        assertEquals("movie title 2", result.get(2).getTitle());
    }
}

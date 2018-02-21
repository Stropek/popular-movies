package com.example.pscurzytek.popularmovies;

import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.utils.MovieUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;


import static junit.framework.Assert.assertEquals;

public class MovieUtilsUnitTest {

    @Test
    public void convertToMovie_nullJsonObject_returnsNull() throws JSONException {
        // when
        Movie result = MovieUtils.convertToMovie(null);

        // then
        assertEquals(null, result);
    }

    @Test
    public void convertToMovie_validJsonObject_returnsMovie() throws JSONException {
        // given
        String json = "{\n" +
                "            \"vote_count\": 10,\n" +
                "            \"id\": 1,\n" +
                "            \"video\": false,\n" +
                "            \"vote_average\": 10.5,\n" +
                "            \"title\": \"movie title\",\n" +
                "            \"popularity\": 15.8,\n" +
                "            \"poster_path\": \"/path.jpg\",\n" +
                "            \"original_language\": \"hi\",\n" +
                "            \"original_title\": \"title in spanish\",\n" +
                "            \"genre_ids\": [\n" +
                "                2,\n" +
                "                test,\n" +
                "                4\n" +
                "            ],\n" +
                "            \"backdrop_path\": \"/backdrop.jpg\",\n" +
                "            \"adult\": false,\n" +
                "            \"overview\": \"movie overview\",\n" +
                "            \"release_date\": \"1995-10-20\"\n" +
                "        }";

        JSONObject jsonObject = new JSONObject(json);

        // when
        Movie result = MovieUtils.convertToMovie(jsonObject);

        // then
        assertEquals(1, result.getId());
        assertEquals("movie title", result.getTitle());
        assertEquals(10, result.getVoteCount());
        assertEquals(10.5, result.getVoteAverage());
        assertEquals(15.8, result.getPopularity());
        assertEquals("/path.jpg", result.getPosterPath());
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
}

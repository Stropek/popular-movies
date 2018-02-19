package com.example.pscurzytek.popularmovies;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.pscurzytek.popularmovies.services.MovieService;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MovieServiceTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.pscurzytek.popularmovies", appContext.getPackageName());
    }

    @Test
    public void getPopular_returnsListOfFilmsWithDescendingPopularity() {
        // given
        MovieService movieService = new MovieService();

        // when

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

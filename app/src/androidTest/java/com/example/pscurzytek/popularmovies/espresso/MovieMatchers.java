package com.example.pscurzytek.popularmovies.espresso;

import android.support.test.espresso.matcher.BoundedMatcher;

import com.example.pscurzytek.popularmovies.models.Movie;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class MovieMatchers {

    public static Matcher<Object> withMovieId(final int id) {
        return new BoundedMatcher<Object, Movie>(Movie.class) {

            @Override
            protected boolean matchesSafely(Movie item) {
                return id == item.getId();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(String.format("Grid View should contain a movie with id: %s", id));
            }
        };
    }
}

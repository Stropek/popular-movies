package com.example.pscurzytek.popularmovies.espresso;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.example.pscurzytek.popularmovies.models.Movie;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MovieMatchers {

    public static Matcher<Movie> withMovieId(final int id) {
        return new TypeSafeMatcher<Movie>(Movie.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText(String.format("Grid View should contain a movie with id: %s", id));
            }

            @Override
            protected boolean matchesSafely(Movie item) {
                return id == item.getId();
            }
        };
    }

    public static Matcher<View> withAdaptedData(final Matcher<Movie> dataMatcher) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Grid View should not contain this movie");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }

                @SuppressWarnings("rawtypes")
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }

                return false;
            }
        };
    }
}

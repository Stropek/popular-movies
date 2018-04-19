package com.example.pscurzytek.popularmovies.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.models.Movie;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MovieDetailsActivityTests
{
    @Rule
    public ActivityTestRule<MovieDetailsActivity> testRule = new ActivityTestRule<>(MovieDetailsActivity.class, false, false);

    @Test
    public void default_displayMovieDetails() {
        // given
        Intent intent = new Intent();
        Movie movie = new Movie(1, "title", 10, 5.0, 7.0, "/posterPath", "en",
                "original title",false, "backdrop path",false, "overview", "rd", null);

        intent.putExtra(Constants.IntentKeys.MovieData, movie);

        // when
        testRule.launchActivity(intent);

        // then
        onView(withId(R.id.title_tv)).check(matches(withText("title")));
        onView(withId(R.id.poster_iv)).check(matches(withContentDescription("http://image.tmdb.org/t/p/w185/posterPath")));
        onView(withId(R.id.releaseDate_tv)).check(matches(withText("rd")));
        onView(withId(R.id.voteAverage_tv)).check(matches(withText("5.0")));
        onView(withId(R.id.plot_tv)).check(matches(withText("overview")));
    }

    @Test
    public void favoriteButton_setsAndUnsetsMovieAsFavorite() {
        // given
        Intent intent = new Intent();
        Movie movie = new Movie(1, "title", 10, 5.0, 7.0, "/posterPath", "en",
                "original title",false, "backdrop path",false, "overview", "rd", null);

        intent.putExtra(Constants.IntentKeys.MovieData, movie);

        // when
        testRule.launchActivity(intent);

        // then
        onView(allOf(withId(R.id.favorite_iv), withTagValue(is((Object) "notFavorite")))).check(matches(isDisplayed()));
        onView(withId(R.id.favorite_iv)).perform(click());
        onView(allOf(withId(R.id.favorite_iv), withTagValue(is((Object) "favorite")))).check(matches(isDisplayed()));
        onView(withId(R.id.favorite_iv)).perform(click());
        onView(allOf(withId(R.id.favorite_iv), withTagValue(is((Object) "notFavorite")))).check(matches(isDisplayed()));
    }
}

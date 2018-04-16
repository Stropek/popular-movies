package com.example.pscurzytek.popularmovies.activities;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.pscurzytek.popularmovies.DaggerTestAppComponent;
import com.example.pscurzytek.popularmovies.PopularMoviesApp;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.TestAppComponent;
import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.services.MovieService;
import com.example.pscurzytek.popularmovies.services.TestMovieServiceModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static com.example.pscurzytek.popularmovies.utils.MovieMatchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Inject
    MovieService movieService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PopularMoviesApp app = (PopularMoviesApp) InstrumentationRegistry.getTargetContext().getApplicationContext();

        TestAppComponent testAppComponent = DaggerTestAppComponent.builder()
                .movieServiceModule(new TestMovieServiceModule())
                .build();

        app.appComponent = testAppComponent;
        testAppComponent.inject(this);
    }

    @Test
    public void default_displaysMostPopularMovies() {
        // given
        List<Movie> movies = createMovies(10);
        when(movieService.getPopular(null)).thenReturn(movies);

        // when
        testRule.launchActivity(null);

        // then
        onView(withId(R.id.thumbnails_grid)).check(matches(withAdaptedData(withMovieId(10))));
    }

    @Test
    public void toggleSortOrder_displaysHighestRatedMovies() {
        // given
        List<Movie> movies = createMovies(10);
        when(movieService.getTopRated(null)).thenReturn(movies);

        testRule.launchActivity(null);
        onView(withId(R.id.thumbnails_grid)).check(matches(not(withAdaptedData(withMovieId(1)))));

        // when
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("Top rated")).perform(click());

        // then
        onView(withId(R.id.thumbnails_grid)).check(matches(withAdaptedData(withMovieId(10))));
    }

    @Test
    public void clickThumbnail_displaysMovieDetails() {
        // given
        when(movieService.getPopular(null)).thenReturn(createMovies(1));

        testRule.launchActivity(null);

        // when
        onData(withMovieId(1)).perform(click());

        // then
        onView(withText("Movie details"));
    }

    private Movie createMovie(int id, String posterPath) {
        String title = String.format("title %s", id);
        int voteCount = id * 10;
        double voteAverage = id * 10 / 2.0;
        int popularity = id * 100;

        return new Movie(id, title, voteCount, voteAverage, popularity, posterPath, "en",
                title, false, "", false, "overview", "", null);
    }

    private List<Movie> createMovies(int count) {
        List<Movie> movies = new ArrayList<>();

        for (int i = 1; i < count + 1; i++) {
            movies.add(createMovie(i, getPosterPath(i)));
        }

        return movies;
    }

    private String getPosterPath(int id) {
        switch (id%4) {
            case 0:
                return "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg";
            case 1:
                return "/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg";
            case 2:
                return "/k4FwHlMhuRR5BISY2Gm2QZHlH5Q.jpg";
            default:
                return "/b6ZJZHUdMEFECvGiDpJjlfUWela.jpg";
        }
    }
}

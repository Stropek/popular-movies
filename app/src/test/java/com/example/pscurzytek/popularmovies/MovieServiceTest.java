package com.example.pscurzytek.popularmovies;

import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.services.MovieService;
import com.pyruby.stubserver.StubServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.pyruby.stubserver.StubMethod.get;
import static junit.framework.Assert.assertEquals;

public class MovieServiceTest {

    private StubServer _server;

    @Before
    public void setUp() {
        _server = new StubServer(5000);
        _server.start();
    }

    @After
    public void cleanUp() {
        _server.stop();
    }

    @Test
    public void useMockMovieApi() {
        if (BuildConfig.BUILD_TYPE.contentEquals("staging")) {
            assertEquals("http://localhost:5000", BuildConfig.MOVIE_DATABASE_BASE_URL);
        } else {
            assertEquals("https://api.themoviedb.org/3/movie", BuildConfig.MOVIE_DATABASE_BASE_URL);
        }
    }

    @Test
    public void getPopular_returnsListOfFilmsWithDescendingPopularity() {
        // given
        _server.expect(get("/popular?api_key=1234567890&language=en-US&page=1"))
                .thenReturn(200, "application/json", SampleJsonResponses.MoviesPageResponse);
        MovieService movieService = new MovieService();

        // when
        List<Movie> result = movieService.getPopular(null);

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void getPopular_pageProvided_returnsListOfFilmsWithDescendingPopularity() {
        // given
        _server.expect(get("/popular?api_key=1234567890&language=en-US&page=3"))
                .thenReturn(200, "application/json", SampleJsonResponses.MoviesPageResponse);
        MovieService movieService = new MovieService();

        // when
        List<Movie> result = movieService.getPopular(3);

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void getTopRated_returnsListOfFilmsWithDescendingRate() {
        // given
        _server.expect(get("/top_rated?api_key=1234567890&language=en-US&page=1"))
                .thenReturn(200, "application/json", SampleJsonResponses.MoviesPageResponse);
        MovieService movieService = new MovieService();

        // when
        List<Movie> result = movieService.getTopRated(null);

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void getTopRated_pageProvided_returnsListOfFilmsWithDescendingRate() {
//        // given
//        _server.expect(get("/top_rated?api_key=1234567890&language=en-US&page=3"))
//                .thenReturn(200, "application/json", SampleJsonResponses.MoviesPageResponse);
//        MovieService movieService = new MovieService();
//
//        // when
//        List<Movie> result = movieService.getTopRated(3);
//
//        // then
//        assertEquals(2, result.size());
    }
}

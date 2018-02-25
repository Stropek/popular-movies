package com.example.pscurzytek.popularmovies;

import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.services.MovieService;
import com.github.tomakehurst.wiremock.WireMockServer;

import org.junit.*;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static junit.framework.Assert.assertEquals;

public class MovieServiceTest {

    private final WireMockServer _wireMockServer = new WireMockServer();

    @Before
    public void testSetUp() {
        _wireMockServer.start();
    }

    @After
    public void testTearDown() {
        _wireMockServer.stop();
    }

    @Test
    public void useMockMovieApi() {
        if (BuildConfig.BUILD_TYPE.contentEquals("staging")) {
            assertEquals("http://localhost:8080", BuildConfig.MOVIE_DATABASE_BASE_URL);
        } else {
            assertEquals("https://api.themoviedb.org/3/movie", BuildConfig.MOVIE_DATABASE_BASE_URL);
        }
    }

    @Test
    public void getPopular_returnsListOfFilmsWithDescendingPopularity() {
        // given
        stubFor(get(urlPathEqualTo("/popular"))
                .withQueryParam("api_key", equalTo("1234567890"))
                .withQueryParam("language", equalTo("en-US"))
                .withQueryParam("page", equalTo("1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(MockResponses.MoviesPageResponse)));

        MovieService movieService = new MovieService();

        // when
        List<Movie> result = movieService.getPopular(null);

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void getPopular_pageProvided_returnsListOfFilmsWithDescendingPopularity() {
        // given
        stubFor(get(urlPathEqualTo("/popular"))
                .withQueryParam("api_key", equalTo("1234567890"))
                .withQueryParam("language", equalTo("en-US"))
                .withQueryParam("page", equalTo("3"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(MockResponses.MoviesPageResponse)));

        MovieService movieService = new MovieService();

        // when
        List<Movie> result = movieService.getPopular(3);

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void getTopRated_returnsListOfFilmsWithDescendingRate() {
        // given
        stubFor(get(urlPathEqualTo("/top_rated"))
                .withQueryParam("api_key", equalTo("1234567890"))
                .withQueryParam("language", equalTo("en-US"))
                .withQueryParam("page", equalTo("1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(MockResponses.MoviesPageResponse)));

        MovieService movieService = new MovieService();

        // when
        List<Movie> result = movieService.getTopRated(null);

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void getTopRated_pageProvided_returnsListOfFilmsWithDescendingRate() {
        // given
        stubFor(get(urlPathEqualTo("/top_rated"))
                .withQueryParam("api_key", equalTo("1234567890"))
                .withQueryParam("language", equalTo("en-US"))
                .withQueryParam("page", equalTo("3"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(MockResponses.MoviesPageResponse)));

        MovieService movieService = new MovieService();

        // when
        List<Movie> result = movieService.getTopRated(3);

        // then
        assertEquals(2, result.size());
    }
}

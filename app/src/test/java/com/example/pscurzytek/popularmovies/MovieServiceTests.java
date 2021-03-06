package com.example.pscurzytek.popularmovies;

import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.models.Review;
import com.example.pscurzytek.popularmovies.models.Trailer;
import com.example.pscurzytek.popularmovies.services.MovieService;
import com.github.tomakehurst.wiremock.WireMockServer;

import org.junit.*;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static junit.framework.Assert.assertEquals;

public class MovieServiceTests {

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
    public void getTrailers_validMovieId_returnsListOfTrailers() {
        // given
        stubFor(get(urlPathEqualTo("/10/videos"))
                .withQueryParam("api_key", equalTo("1234567890"))
                .withQueryParam("language", equalTo("en-US"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(MockResponses.TrailersListResponse))
        );

        MovieService movieService = new MovieService();

        // when
        List<Trailer> result = movieService.getTrailers(10);

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void getReviews_returnsListOfReviews() {
        // given
        stubFor(get(urlPathEqualTo("/10/reviews"))
                .withQueryParam("api_key", equalTo("1234567890"))
                .withQueryParam("language", equalTo("en-US"))
                .withQueryParam("page", equalTo("1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(MockResponses.ReviewsPageResponse)));

        MovieService movieService = new MovieService();

        // when
        List<Review> result = movieService.getReviews(10,null);

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void getPopular_pageProvided_returnsListOfReviews() {
        // given
        stubFor(get(urlPathEqualTo("/10/reviews"))
                .withQueryParam("api_key", equalTo("1234567890"))
                .withQueryParam("language", equalTo("en-US"))
                .withQueryParam("page", equalTo("3"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(MockResponses.ReviewsPageResponse)));

        MovieService movieService = new MovieService();

        // when
        List<Review> result = movieService.getReviews(10,3);

        // then
        assertEquals(2, result.size());
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
        assertEquals(3, result.size());
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
        assertEquals(3, result.size());
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
        assertEquals(3, result.size());
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
        assertEquals(3, result.size());
    }
}

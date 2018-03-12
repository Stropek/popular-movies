package com.example.pscurzytek.popularmovies.services;

import org.mockito.Mockito;

public class TestMovieServiceModule extends MovieServiceModule {

    @Override
    public MovieService provideMovieService() {
        return Mockito.mock(MovieService.class);
    }
}

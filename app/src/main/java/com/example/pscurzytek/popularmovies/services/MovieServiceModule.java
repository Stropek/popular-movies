package com.example.pscurzytek.popularmovies.services;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieServiceModule {

    @Provides
    @Singleton
    public MovieService provideMovieService() {
        return new MovieService();
    }
}

package com.example.pscurzytek.popularmovies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private PopularMoviesApp app;

    public AppModule(PopularMoviesApp app) {
        this.app = app;
    }

    @Provides @Singleton
    PopularMoviesApp provideApp() {
        return new PopularMoviesApp();
    }
}

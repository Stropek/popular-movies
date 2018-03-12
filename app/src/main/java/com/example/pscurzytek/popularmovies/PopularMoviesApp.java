package com.example.pscurzytek.popularmovies;


import android.app.Application;

import com.example.pscurzytek.popularmovies.services.MovieServiceModule;

public class PopularMoviesApp extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .movieServiceModule(new MovieServiceModule())
                .build();

        appComponent.inject(this);
    }
}

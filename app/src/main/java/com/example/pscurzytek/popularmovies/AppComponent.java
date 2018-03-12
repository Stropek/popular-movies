package com.example.pscurzytek.popularmovies;

import com.example.pscurzytek.popularmovies.activities.MainActivity;
import com.example.pscurzytek.popularmovies.services.MovieServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, MovieServiceModule.class} )
public interface AppComponent {

    void inject(PopularMoviesApp app);

    void inject(MainActivity activity);
}

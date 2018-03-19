package com.example.pscurzytek.popularmovies;

import com.example.pscurzytek.popularmovies.fragments.MovieListFragment;
import com.example.pscurzytek.popularmovies.services.MovieServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { MovieServiceModule.class} )
public interface AppComponent {

    void inject(PopularMoviesApp app);

    void inject(MovieListFragment movieListFragment);
}

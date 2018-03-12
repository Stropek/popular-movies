package com.example.pscurzytek.popularmovies;

import com.example.pscurzytek.popularmovies.activities.MainActivityTests;
import com.example.pscurzytek.popularmovies.services.MovieServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { MovieServiceModule.class } )
public interface TestAppComponent extends AppComponent {

    void inject(MainActivityTests test);
}

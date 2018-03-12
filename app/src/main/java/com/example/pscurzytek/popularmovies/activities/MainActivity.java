package com.example.pscurzytek.popularmovies.activities;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.pscurzytek.popularmovies.PopularMoviesApp;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.adapters.MovieAdapter;
import com.example.pscurzytek.popularmovies.enums.SortOrder;
import com.example.pscurzytek.popularmovies.loaders.MovieLoader;
import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.services.MovieService;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<List<Movie>>{

    private static final int MOVIE_LOADER_ID = 1;

    private SortOrder sortOrder = SortOrder.MostPopular;

    @Inject
    public MovieService movieService;

    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PopularMoviesApp app = (PopularMoviesApp) getApplication();
        app.appComponent.inject(this);

        movieAdapter = new MovieAdapter(this);

        GridView thumbnails = findViewById(R.id.thumbnails_grid);
        thumbnails.setAdapter(movieAdapter);

        getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SortOrder current = sortOrder;

        switch (item.getItemId()) {
            case R.id.action_sort_topRated:
                sortOrder = SortOrder.TopRated;
                break;
            case R.id.action_sort_mostPopular:
                sortOrder = SortOrder.MostPopular;
                break;
        }

        if (current != sortOrder) {
            getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
        }

        return true;
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieLoader(this, movieService, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        movieAdapter.clear();
        movieAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        movieAdapter.clear();
    }
}

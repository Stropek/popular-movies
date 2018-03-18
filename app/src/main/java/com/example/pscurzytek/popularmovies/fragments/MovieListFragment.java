package com.example.pscurzytek.popularmovies.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.PopularMoviesApp;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.activities.MovieDetailsActivity;
import com.example.pscurzytek.popularmovies.adapters.MovieAdapter;
import com.example.pscurzytek.popularmovies.enums.SortOrder;
import com.example.pscurzytek.popularmovies.loaders.MovieLoader;
import com.example.pscurzytek.popularmovies.models.Movie;
import com.example.pscurzytek.popularmovies.services.MovieService;

import java.util.List;

import javax.inject.Inject;

public class MovieListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Movie>> {

    @Inject
    public MovieService movieService;

    private static final int MOVIE_LOADER_ID = 1;
    private SortOrder sortOrder = SortOrder.MostPopular;

    private Context context;
    private MovieAdapter movieAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PopularMoviesApp app = (PopularMoviesApp) getActivity().getApplication();
        app.appComponent.inject(this);

        context = getContext();
        movieAdapter = new MovieAdapter(context);

        Bundle arguments = getArguments();
        if (arguments != null) {
            sortOrder = (SortOrder) arguments.get(Constants.BundleKeys.SortOrder);
        }

        getLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final GridView thumbnails = container.findViewById(R.id.thumbnails_grid);
        thumbnails.setAdapter(movieAdapter);
        thumbnails.setClickable(true);
        thumbnails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) thumbnails.getItemAtPosition(position);

                Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                intent.putExtra(Constants.IntentKeys.MovieData, movie);

                startActivity(intent);
            }
        });

        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieLoader(context, movieService, sortOrder);
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

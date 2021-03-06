package com.example.pscurzytek.popularmovies.fragments;

import android.app.Activity;
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
import com.example.pscurzytek.popularmovies.adapters.MovieArrayAdapter;
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
    private Movie currentMovie;

    private Activity activity;
    private MovieArrayAdapter movieArrayAdapter;

    private OnMoviesLoadedListener moviesLoadedListener;
    private OnMovieSelectedListener movieSelectedListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();

        PopularMoviesApp app = (PopularMoviesApp) activity.getApplication();
        app.appComponent.inject(this);

        movieArrayAdapter = new MovieArrayAdapter(activity);
        loadList(savedInstanceState);

        getLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadList(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(Constants.StateKeys.Movie, currentMovie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        final GridView thumbnails = view.findViewById(R.id.thumbnails_grid);
        thumbnails.setAdapter(movieArrayAdapter);
        thumbnails.setClickable(true);
        thumbnails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) thumbnails.getItemAtPosition(position);

                if (movieSelectedListener.isBigScreen()) {
                    movieSelectedListener.onMovieSelected(movie);
                } else {
                    Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                    intent.putExtra(Constants.IntentKeys.MovieData, movie);

                    startActivity(intent);
                }
            }
        });

        return view;
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieLoader(activity, movieService, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        movieArrayAdapter.clear();
        movieArrayAdapter.addAll(data);
        if (data.size() > 0 && currentMovie == null) {
            moviesLoadedListener.onMoviesLoaded(data.get(0));
        } else {
            moviesLoadedListener.onMoviesLoaded(currentMovie);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        movieArrayAdapter.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            moviesLoadedListener = (OnMoviesLoadedListener) context;
            movieSelectedListener = (OnMovieSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    public void setCurrentMovie(Movie movie) {
        if (movie != null) {
            currentMovie = movie;
        }
    }

    public interface OnMoviesLoadedListener {
        void onMoviesLoaded(Movie movie);
    }

    public interface OnMovieSelectedListener {
        void onMovieSelected(Movie movie);

        Boolean isBigScreen();
    }

    private void loadList(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            currentMovie = (Movie) getArguments().getSerializable(Constants.BundleKeys.MovieDetails);
            sortOrder = (SortOrder) getArguments().get(Constants.BundleKeys.SortOrder);
        } else {
            currentMovie = (Movie) savedInstanceState.getSerializable(Constants.StateKeys.Movie);
            sortOrder = (SortOrder) savedInstanceState.get(Constants.StateKeys.SortOrder);
        }
    }
}

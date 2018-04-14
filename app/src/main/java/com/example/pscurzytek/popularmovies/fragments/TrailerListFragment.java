package com.example.pscurzytek.popularmovies.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.PopularMoviesApp;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.adapters.TrailerRecyclerAdapter;
import com.example.pscurzytek.popularmovies.loaders.TrailerLoader;
import com.example.pscurzytek.popularmovies.models.Trailer;
import com.example.pscurzytek.popularmovies.services.MovieService;

import java.util.List;

import javax.inject.Inject;

public class TrailerListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Trailer>> {

    @Inject
    public MovieService movieService;

    private static final int TRAILER_LOADER_ID = 2;

    private int movieId;

    private Activity activity;
    private TrailerRecyclerAdapter trailerRecyclerAdapter;

    public static TrailerListFragment newInstance(int movieId) {
        Bundle args = new Bundle();

        args.putInt(Constants.BundleKeys.MovieId, movieId);

        TrailerListFragment fragment = new TrailerListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();

        PopularMoviesApp app = (PopularMoviesApp) activity.getApplication();
        app.appComponent.inject(this);

        trailerRecyclerAdapter = new TrailerRecyclerAdapter(activity);

        Bundle arguments = getArguments();
        movieId = arguments.getInt(Constants.BundleKeys.MovieId);

        getLoaderManager().initLoader(TRAILER_LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(trailerRecyclerAdapter);

        return view;
    }

    @Override
    public Loader<List<Trailer>> onCreateLoader(int id, Bundle args) {
        return new TrailerLoader(activity, movieService, movieId);
    }

    @Override
    public void onLoadFinished(Loader<List<Trailer>> loader, List<Trailer> data) {
        trailerRecyclerAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Trailer>> loader) {
        trailerRecyclerAdapter.swapData(null);
    }
}

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
import com.example.pscurzytek.popularmovies.adapters.ReviewRecyclerAdapter;
import com.example.pscurzytek.popularmovies.loaders.ReviewLoader;
import com.example.pscurzytek.popularmovies.models.Review;
import com.example.pscurzytek.popularmovies.services.MovieService;

import java.util.List;

import javax.inject.Inject;

public class ReviewListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Review>> {

    @Inject
    public MovieService movieService;
    private static final int REVIEW_LOADER_ID = 3;

    private int movieId;

    private Activity activity;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;

    public static ReviewListFragment newInstance(int movieId) {
        Bundle args = new Bundle();

        args.putInt(Constants.BundleKeys.MovieId, movieId);

        ReviewListFragment fragment = new ReviewListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);activity = getActivity();
        activity = getActivity();

        PopularMoviesApp app = (PopularMoviesApp) activity.getApplication();
        app.appComponent.inject(this);

        reviewRecyclerAdapter = new ReviewRecyclerAdapter(activity);

        Bundle arguments = getArguments();
        movieId = arguments.getInt(Constants.BundleKeys.MovieId);

        getLoaderManager().initLoader(REVIEW_LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(reviewRecyclerAdapter);

        return view;
    }

    @Override
    public Loader<List<Review>> onCreateLoader(int id, Bundle args) {
        return new ReviewLoader(activity, movieService, movieId);
    }

    @Override
    public void onLoadFinished(Loader<List<Review>> loader, List<Review> data) {
        reviewRecyclerAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Review>> loader) {
        reviewRecyclerAdapter.swapData(null);
    }
}

package com.example.pscurzytek.popularmovies.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.enums.SortOrder;
import com.example.pscurzytek.popularmovies.fragments.MovieDetailsFragment;
import com.example.pscurzytek.popularmovies.fragments.MovieListFragment;
import com.example.pscurzytek.popularmovies.models.Movie;

public class MainActivity extends AppCompatActivity
    implements MovieListFragment.OnMoviesLoadedListener, MovieListFragment.OnMovieSelectedListener {

    private SortOrder sortOrder = SortOrder.MostPopular;
    private MovieDetailsFragment detailsFragment;
    private MovieListFragment listFragment;

    private boolean isBigScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isBigScreen = findViewById(R.id.movie_details_fl) != null;

        if (savedInstanceState == null) {
            loadMainFragments();
        } else {
            sortOrder = (SortOrder) savedInstanceState.get(Constants.StateKeys.SortOrder);
            listFragment = (MovieListFragment) getSupportFragmentManager().getFragment(savedInstanceState, Constants.StateKeys.MovieListFragment);
            if (isBigScreen) {
                detailsFragment = (MovieDetailsFragment) getSupportFragmentManager().getFragment(savedInstanceState, Constants.StateKeys.MovieDetailsFragment);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(Constants.StateKeys.SortOrder, sortOrder);
        getSupportFragmentManager().putFragment(outState, Constants.StateKeys.MovieListFragment, listFragment);
        if (isBigScreen) {
            getSupportFragmentManager().putFragment(outState, Constants.StateKeys.MovieDetailsFragment, detailsFragment);
        }
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
            case R.id.action_sort_favorite:
                sortOrder = SortOrder.Favorite;
                break;
        }

        if (current != sortOrder) {
            loadMainFragments();
        }

        return true;
    }

    @Override
    public void onMoviesLoaded(final Movie movie) {
        if (isBigScreen) {
            loadDetailsFragment(movie);
        }
    }

    @Override
    public void onMovieSelected(Movie movie) {
        if (isBigScreen) {
            loadDetailsFragment(movie);
            listFragment.setCurrentMovie(movie);
        }
    }

    @Override
    public Boolean isBigScreen() {
        return isBigScreen;
    }

    public void onReviewClicked(View view) {
        OnClickHandlers.onReviewClicked(view);
    }

    public void onFavoriteClicked(View view) {
        OnClickHandlers.onFavoriteClicked(view, getResources(), detailsFragment.getMovie());
    }

    private void loadMainFragments() {
        listFragment = getListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.movie_list_fl, listFragment);
        fragmentTransaction.commit();

        invalidateOptionsMenu();
    }

    private void loadDetailsFragment(final Movie movie) {
        detailsFragment = getDetailsFragment(movie);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.movie_details_fl, detailsFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private MovieListFragment getListFragment() {
        MovieListFragment fragment = new MovieListFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleKeys.SortOrder, sortOrder);
        fragment.setArguments(bundle);

        return fragment;
    }

    private MovieDetailsFragment getDetailsFragment(Movie movie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleKeys.MovieDetails, movie);
        fragment.setArguments(bundle);

        return fragment;
    }
}

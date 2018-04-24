package com.example.pscurzytek.popularmovies.activities;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.fragments.MovieDetailsFragment;
import com.example.pscurzytek.popularmovies.models.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    private Handler handler;
    private Movie movie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handler = new Handler();
        movie = (Movie) getIntent().getSerializableExtra(Constants.IntentKeys.MovieData);

        loadDetailsFragments();
    }

    public void onReviewClicked(View view) {
        OnClickHandlers.onReviewClicked(view);
    }

    public void onFavoriteClicked(View view) {
        OnClickHandlers.onFavoriteClicked(view, getResources(), movie);
    }

    private void loadDetailsFragments() {
        Runnable pendingRunnable = new Runnable() {
            public void run() {  // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.movie_details_fl, fragment);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        handler.post(pendingRunnable);

        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        Fragment fragment = new MovieDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleKeys.MovieDetails, movie);
        fragment.setArguments(bundle);

        return fragment;
    }
}

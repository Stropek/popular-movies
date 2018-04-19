package com.example.pscurzytek.popularmovies.activities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.data.MovieContract;
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
        TextView content = view.findViewById(R.id.content_tv);
        if (content.getMaxLines() == 2) {
            content.setMaxLines(Integer.MAX_VALUE);
        } else {
            content.setMaxLines(2);
        }
    }

    public void onFavoriteClicked(View view) {
        ImageView favoriteStar = (ImageView) view;
        String tag = (String) favoriteStar.getTag();
        ContentResolver contentResolver = view.getContext().getContentResolver();

        if (tag.equals("favorite")) {
            Uri deleteUri = MovieContract.MovieEntry.CONTENT_URI.buildUpon().appendPath(movie.getIdAsString()).build();
            contentResolver.delete(deleteUri, null, null);

            favoriteStar.setTag("notFavorite");
            favoriteStar.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_off));
        } else {
            Uri createUri = MovieContract.MovieEntry.CONTENT_URI;
            contentResolver.insert(createUri, movie.toContentValues());

            favoriteStar.setTag("favorite");
            favoriteStar.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_on));
        }
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

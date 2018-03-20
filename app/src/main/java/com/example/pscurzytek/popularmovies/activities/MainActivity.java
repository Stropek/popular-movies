package com.example.pscurzytek.popularmovies.activities;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.enums.SortOrder;
import com.example.pscurzytek.popularmovies.fragments.MovieDetailsFragment;
import com.example.pscurzytek.popularmovies.fragments.MovieListFragment;
import com.example.pscurzytek.popularmovies.models.Movie;

public class MainActivity extends AppCompatActivity {

    private SortOrder sortOrder = SortOrder.MostPopular;
    private Handler handler;
    private boolean bigScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        bigScreen = findViewById(R.id.movie_details_fl) != null;

        loadMainFragments();
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
            loadMainFragments();
        }

        return true;
    }

    private void loadMainFragments() {
        Fragment listFragment = getListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.movie_list_fl, listFragment);
        fragmentTransaction.commit();

        if (bigScreen) {
//            Runnable detailsFragment = new Runnable() {
//                public void run() {  // update the main content by replacing fragments
//                    Fragment fragment = getDetailsFragment();
//                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//                    fragmentTransaction.replace(R.id.movie_details_fl, fragment);
//                    fragmentTransaction.commitAllowingStateLoss();
//                }
//            };
//
//            handler.post(detailsFragment);
        }

        invalidateOptionsMenu();
    }

    private Fragment getListFragment() {
        Fragment fragment = new MovieListFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleKeys.SortOrder, sortOrder);
        fragment.setArguments(bundle);

        return fragment;
    }

    private Fragment getDetailsFragment() {
        Fragment fragment = new MovieDetailsFragment();
        final GridView thumbnails = findViewById(R.id.thumbnails_grid);
        Movie movie = (Movie) thumbnails.getItemAtPosition(0);

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleKeys.MovieDetails, movie);
        fragment.setArguments(bundle);

        return fragment;
    }
}

package com.example.pscurzytek.popularmovies.activities;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.enums.SortOrder;
import com.example.pscurzytek.popularmovies.fragments.MovieListFragment;

public class MainActivity extends AppCompatActivity {

    private SortOrder sortOrder = SortOrder.MostPopular;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

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
        Runnable pendingRunnable = new Runnable() {
            public void run() {  // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.movie_list_fl, fragment);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        handler.post(pendingRunnable);

        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        Fragment fragment = new MovieListFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleKeys.SortOrder, sortOrder);
        fragment.setArguments(bundle);

        return fragment;
    }
}

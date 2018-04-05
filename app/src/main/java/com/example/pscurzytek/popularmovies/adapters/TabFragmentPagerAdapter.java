package com.example.pscurzytek.popularmovies.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pscurzytek.popularmovies.fragments.ReviewListFragment;
import com.example.pscurzytek.popularmovies.fragments.TrailerListFragment;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;

    private final String[] tabTitles = new String[] { "Trailers", "Reviews" };
    private final Context context;
    private final int movieId;

    public TabFragmentPagerAdapter(FragmentManager fragmentManager, Context context, int movieId) {
        super(fragmentManager);
        this.context = context;
        this.movieId = movieId;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return TrailerListFragment.newInstance(movieId);
            case 1: return ReviewListFragment.newInstance(movieId);
        }

        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}

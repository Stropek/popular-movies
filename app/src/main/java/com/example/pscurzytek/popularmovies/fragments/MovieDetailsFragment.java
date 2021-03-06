package com.example.pscurzytek.popularmovies.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.adapters.TabFragmentPagerAdapter;
import com.example.pscurzytek.popularmovies.data.MovieContract;
import com.example.pscurzytek.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {

    private Movie movie;
    private Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        loadMovie(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadMovie(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(Constants.StateKeys.Movie, movie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        TextView titleTextView = view.findViewById(R.id.title_tv);
        TextView releaseDateTextView = view.findViewById(R.id.releaseDate_tv);
        TextView voteAverageTextView = view.findViewById(R.id.voteAverage_tv);
        TextView plotTextView = view.findViewById(R.id.plot_tv);
        ImageView posterImageView = view.findViewById(R.id.poster_iv);
        ImageView favoriteImageView = view.findViewById(R.id.favorite_iv);

        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            releaseDateTextView.setText(movie.getReleaseDate());
            voteAverageTextView.setText(String.format("%s", movie.getVoteAverage()));
            plotTextView.setText(movie.getOverview());
            posterImageView.setContentDescription(movie.getFullPosterPath());

            Picasso.with(activity)
                    .load(movie.getFullPosterPath())
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_placeholder)
                    .into(posterImageView);

            Uri movieUri = MovieContract.MovieEntry.CONTENT_URI.buildUpon().appendPath(movie.getIdAsString()).build();
            Cursor cursor = getContext().getContentResolver().query(movieUri, null, null, null, null);

            if (cursor.getCount() > 0) {
                favoriteImageView.setTag("favorite");
                favoriteImageView.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_on));
            }

            cursor.close();

            ViewPager viewPager = view.findViewById(R.id.movie_details_vp);
            viewPager.setAdapter(new TabFragmentPagerAdapter(getChildFragmentManager(), activity, movie.getId()));

            TabLayout tabLayout = view.findViewById(R.id.movie_details_tabs);
            tabLayout.setupWithViewPager(viewPager);
        }

        return view;
    }

    public Movie getMovie() {
        return movie;
    }

    private void loadMovie(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            movie = (Movie) getArguments().getSerializable(Constants.BundleKeys.MovieDetails);
        } else {
            movie = (Movie) savedInstanceState.getSerializable(Constants.StateKeys.Movie);
        }
    }
}

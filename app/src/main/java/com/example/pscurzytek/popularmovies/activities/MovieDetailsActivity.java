package com.example.pscurzytek.popularmovies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pscurzytek.popularmovies.Constants;
import com.example.pscurzytek.popularmovies.R;
import com.example.pscurzytek.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = (Movie) getIntent().getSerializableExtra(Constants.IntentKeys.MovieData);

        TextView titleTextView = findViewById(R.id.title_tv);
        TextView releaseDateTextView = findViewById(R.id.releaseDate_tv);
        TextView voteAverageTextView = findViewById(R.id.voteAverage_tv);
        TextView plotTextView = findViewById(R.id.plot_tv);
        ImageView posterImageView = findViewById(R.id.poster_iv);

        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            releaseDateTextView.setText(movie.getReleaseDate());
            voteAverageTextView.setText(String.format("%s", movie.getVoteAverage()));
            plotTextView.setText(movie.getOverview());
            posterImageView.setContentDescription(movie.getFullPosterPath());

            Picasso.with(this).load(movie.getFullPosterPath()).into(posterImageView);
        }
    }
}

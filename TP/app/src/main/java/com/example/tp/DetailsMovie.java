package com.example.tp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailsMovie extends AppCompatActivity {
    final String imgBaseUrl = "https://image.tmdb.org/t/p/original";

    Movie movie;
    ArrayList<String> movieGenres;
    ImageView imageMovie;
    TextView description;
    TextView date;
    TextView genres;
    MoviesContext app;
    Button add_to_favorite;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieGenres = (ArrayList<String>) getIntent().getSerializableExtra("genres");
        imageMovie = (ImageView) findViewById(R.id.imageMovie);
        description = (TextView) findViewById(R.id.descriptionMovie);
        date = (TextView) findViewById(R.id.date);
        genres = (TextView) findViewById(R.id.genres);
        add_to_favorite = (Button) findViewById(R.id.add_to_favorite);
        app = (MoviesContext) getApplicationContext();

        setTitle(movie.getTitle());
        add_to_favorite.setVisibility(app.containsMovie(movie) ? View.INVISIBLE : View.VISIBLE);
        Glide.with(this).load(imgBaseUrl+movie.getBackdrop_path()).into(imageMovie);
        date.setText(movie.getRelease_date());
        description.setText(movie.getOverview());
        System.out.println("context movies : " + app.getMovies());
        if(movieGenres != null && movieGenres.size() > 0)
        genres.setText(" - " + String.join("\n - ", movieGenres));

        add_to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!app.containsMovie(movie))
                    if(app.addMovie(movie)){
                        add_to_favorite.setVisibility(View.INVISIBLE);
                        Toast.makeText(DetailsMovie.this, "Movie added to favorite!", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
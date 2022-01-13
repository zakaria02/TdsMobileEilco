package com.example.tp;

import android.app.Application;
import java.util.ArrayList;
import java.util.List;

public class MoviesContext extends Application {
    private List<Movie> movies;

    @Override
    public void onCreate() {
        super.onCreate();
        this.movies = new ArrayList<Movie>();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public boolean addMovie(Movie movie) {
        return this.movies.add(movie);
    }

    public boolean removeMovie(Movie movie){
        return this.movies.remove(movie);
    }

    public boolean containsMovie(Movie movie){
        for(int i=0; i<movies.size(); i++){
            if(movie.equals(movies.get(i)))
                return true;
        }
        return false;
    }
}
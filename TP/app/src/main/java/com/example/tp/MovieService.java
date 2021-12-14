package com.example.tp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    public static final String apiURL = "https://api.themoviedb.org/3/";

    public static final String apiKey = "77f4b8a0d3137442cdca5332b84a12d6";

    @GET("movie/popular?api_key="+apiKey+"&language=en-US&page=1")
    Call<ArrayList<Movie>> listMovies();

    @GET("movie/upcoming?api_key="+apiKey+"&language=en-US&page=1")
    Call<ArrayList<Movie>> upcomingMovies();

}
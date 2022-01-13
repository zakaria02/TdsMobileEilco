package com.example.tp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    public static final String apiURL = "https://api.themoviedb.org/3/";

    public static final String apiKey = "77f4b8a0d3137442cdca5332b84a12d6";

    @GET("movie/popular?api_key="+apiKey)
    Call<ListMovies> listMovies(@Query("language") String language, @Query("page") int page);

    @GET("movie/upcoming?api_key="+apiKey)
    Call<ListMovies> upcomingMovies(@Query("language") String language, @Query("page") int page);

    @GET("genre/movie/list?api_key="+apiKey)
    Call<ListGenres> listGenres();

    @GET("search/movie?api_key="+apiKey)
    Call<ListMovies> moviesSearch(@Query("query") String query, @Query("language") String language);
}
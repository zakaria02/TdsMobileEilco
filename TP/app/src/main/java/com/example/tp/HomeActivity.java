package com.example.tp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    MovieService movieService;
    ArrayList<Movie> moviesList = new ArrayList<Movie>();
    ArrayList<Genre> genresList = new ArrayList<Genre>();
    LinearLayout searchLayout;
    EditText searchMovie;
    Button searchButton;
    RecyclerView rvMovies;
    MoviesAdapter adapter;
    GridLayoutManager layoutManager;
    RecyclerView rvMoviesSearch;
    GridLayoutManager searchLayoutManager;
    Context myContext;
    String language;
    int upComingPage;
    int popularPage;
    boolean isPopular;
    MoviesContext app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        myContext = this;
        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        layoutManager  = new GridLayoutManager(myContext, 2);
        searchLayout = (LinearLayout) findViewById(R.id.search_layout);
        searchLayoutManager  = new GridLayoutManager(myContext, 2);
        searchMovie = (EditText) findViewById(R.id.searchMovie);
        searchButton = (Button) findViewById(R.id.searchButton);
        rvMoviesSearch = (RecyclerView) findViewById(R.id.rvMoviesSearch);
        language = (String) getIntent().getSerializableExtra("language");
        popularPage = 1;
        upComingPage = 1;
        isPopular = true;
        app = (MoviesContext) getApplicationContext();


        movieService = new Retrofit.Builder()
                .baseUrl(MovieService.apiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);

        moviesList();
        genresList();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String itemName = item.getTitle().toString();
                switch (itemName){
                    case "Popular": {
                        popularPage = 1;
                        isPopular = true;
                        moviesList();
                        break;
                    }
                    case "Search": searchLayout(); break;
                    case "Favorite": favoriteMovies(); break;
                    default: {
                        upComingPage = 1;
                        isPopular = false;
                        upComingMovies();
                        break;
                    }
                }
                return true;
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMovies();
            }
        });

        rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    if(isPopular && popularPage < 1000){
                        moviesList();
                    }else if(!isPopular && upComingPage < 1000){
                        upComingMovies();
                    }
                }
            }
        });
    }

    private void upComingMovies(){
        if(upComingPage == 1) moviesList.clear();
        rvMovies.setVisibility(View.VISIBLE);
        searchLayout.setVisibility(View.INVISIBLE);
        movieService.upcomingMovies(language, upComingPage).enqueue(new Callback<ListMovies>() {
            @Override
            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                moviesList.addAll(response.body().getResults());
                adapter = new MoviesAdapter(moviesList, genresList);
                rvMovies.setAdapter(adapter);
                rvMovies.setLayoutManager(layoutManager);
                upComingPage++;
            }

            @Override
            public void onFailure(Call<ListMovies> call, Throwable t) {
                System.out.println("Error " + t.getMessage());
                moviesList = new ArrayList<Movie>();
            }
        });
    }

    private void favoriteMovies(){
        moviesList.clear();
        rvMovies.setVisibility(View.VISIBLE);
        searchLayout.setVisibility(View.INVISIBLE);
        moviesList.addAll(app.getMovies());
        adapter = new MoviesAdapter(moviesList, genresList);
        rvMovies.setAdapter(adapter);
        rvMovies.setLayoutManager(layoutManager);
    }

    private void moviesList(){
        if(popularPage==1) moviesList.clear();
        rvMovies.setVisibility(View.VISIBLE);
        searchLayout.setVisibility(View.INVISIBLE);
        movieService.listMovies(language,popularPage).enqueue(new Callback<ListMovies>() {
            @Override
            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                moviesList.addAll(response.body().getResults());
                adapter = new MoviesAdapter(moviesList, genresList);
                rvMovies.setAdapter(adapter);
                rvMovies.setLayoutManager(layoutManager);
                popularPage++;
            }

            @Override
            public void onFailure(Call<ListMovies> call, Throwable t) {
                System.out.println("Error " + t.getMessage());
                moviesList = new ArrayList<Movie>();
            }
        });
    }

    private void genresList(){
        genresList.clear();
        movieService.listGenres().enqueue(new Callback<ListGenres>() {
            @Override
            public void onResponse(Call<ListGenres> call, Response<ListGenres> response) {
                genresList = response.body().getGenres();
            }

            @Override
            public void onFailure(Call<ListGenres> call, Throwable t) {
                System.out.println("Error " + t.getMessage());
                genresList = new ArrayList<Genre>();
            }
        });
    }

    private void searchLayout(){
        searchLayout.setVisibility(View.VISIBLE);
        rvMovies.setVisibility(View.INVISIBLE);
    }

    private void searchMovies(){
        moviesList.clear();
        movieService.moviesSearch(searchMovie.getText().toString(),language).enqueue(new Callback<ListMovies>() {
            @Override
            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                moviesList = response.body().getResults();
                MoviesAdapter adapter = new MoviesAdapter(moviesList, genresList);
                rvMoviesSearch.setAdapter(adapter);
                rvMoviesSearch.setLayoutManager(searchLayoutManager);

            }

            @Override
            public void onFailure(Call<ListMovies> call, Throwable t) {
                System.out.println("Error " + t.getMessage());
                moviesList = new ArrayList<Movie>();
            }
        });
    }
}
package com.example.tp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    MovieService movieService;
    ArrayList<Movie> moviesList = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        movieService = new Retrofit.Builder()
                .baseUrl(MovieService.apiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String itemName = item.getTitle().toString();
                switch (itemName){
                    case "Popular": moviesList(); break;
                    default: upComingMovies(); break;
                }
                return true;
            }
        });
    }

    private void upComingMovies(){
        System.out.println("Up coming movies");
        movieService.upcomingMovies().enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                moviesList = response.body();
                System.out.println(moviesList.size());
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                System.out.println("Error");
                moviesList = new ArrayList<Movie>();
            }
        });
    }

    private void moviesList(){
        System.out.println("Movies List");
        movieService.listMovies().enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                moviesList = response.body();
                System.out.println(moviesList.size());
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                System.out.println("Error");
                moviesList = new ArrayList<Movie>();
            }
        });
    }
}
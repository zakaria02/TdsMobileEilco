package com.example.tp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    final String imgBaseUrl = "https://image.tmdb.org/t/p/original";
    private final ArrayList<Movie> mMovies;
    private final ArrayList<Genre> mGenres;

    public MoviesAdapter(ArrayList<Movie> movies, ArrayList<Genre> genres){
        mMovies = movies;
        mGenres = genres;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView = inflater.inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        ArrayList<String> movieGenres = new ArrayList<String>();

        ImageView moviePicture = holder.moviePicture;
        Glide.with(holder.itemView).load(imgBaseUrl+movie.getPoster_path()).into(moviePicture);

        moviePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieGenres.clear();
                for(int i=0; i<mGenres.size(); i++){
                    for(int j=0; j<movie.getGenre_ids().size(); j++){
                        if(movie.getGenre_ids().get(j) == mGenres.get(i).getId())
                            movieGenres.add(mGenres.get(i).getName());
                    }
                }
                Intent intent = new Intent(view.getContext(), DetailsMovie.class);
                intent.putExtra("movie", movie);
                intent.putExtra("genres", movieGenres);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView moviePicture;

        public ViewHolder(View itemView){
            super(itemView);

            moviePicture = (ImageView) itemView.findViewById(R.id.moviePicture);
        }
    }
}

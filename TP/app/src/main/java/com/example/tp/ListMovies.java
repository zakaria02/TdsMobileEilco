package com.example.tp;

import java.util.ArrayList;

public class ListMovies {
    private ArrayList<Movie> results;

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        String result="";
        for (Movie v:
                results) {

            result+=" " + v.getTitle();

        }
        return result;
    }
}

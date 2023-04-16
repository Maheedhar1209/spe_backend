package com.iiitb.spe.clientmodels;



import java.util.Arrays;

public class Movie {
    String movie_name;
    String[] ott_platforms;
    String Id;
    String release_date;

    public Movie(String movie_name, String[] ott_platforms, String id, String release_date) {
        this.movie_name = movie_name;
        this.ott_platforms = ott_platforms;
        Id = id;
        this.release_date = release_date;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String[] getOtt_platforms() {
        return ott_platforms;
    }

    public void setOtt_platforms(String[] ott_platforms) {
        this.ott_platforms = ott_platforms;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_name='" + movie_name + '\'' +
                ", ott_platforms=" + Arrays.toString(ott_platforms) +
                ", Id='" + Id + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }
}

package com.iiitb.spe.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.spe.models.Movie_Details;
import com.iiitb.spe.repositories.MovieDetailsRepository;

import java.util.List;

@Service
@Transactional
public class MovieDetailsService {
    @Autowired
    private MovieDetailsRepository repo;


    public Movie_Details findByMovieName(String movie_name)
    {
        return repo.findByMovieName(movie_name);
    }

    public List<Movie_Details> findbyMovieGenre(String movie_genre) {return repo.findByMovieGenre(movie_genre); }
}

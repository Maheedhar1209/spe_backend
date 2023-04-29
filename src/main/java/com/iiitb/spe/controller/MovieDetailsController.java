package com.iiitb.spe.controller;


import java.sql.Date;
import java.util.*;

import com.iiitb.spe.models.Movie_ID;
import com.iiitb.spe.service.MyListDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iiitb.spe.service.MyListDetailsService;
import com.iiitb.spe.service.MovieDetailsService;

import com.iiitb.spe.models.Movie_Details;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class MovieDetailsController {
    @Autowired
    private MovieDetailsService movieDetailsService;
    @Autowired
    private MyListDetailsService mylistdetailsservice;
    @GetMapping("/Movie")
    public ResponseEntity<Object> moviedetails(@RequestParam("movie_name") String movie_name){
        System.out.println("fdfdfdfdfdsdfd");
        System.out.println(movie_name);
        Movie_Details md=movieDetailsService.findByMovieName(movie_name);

        if (md != null) {
            return ResponseEntity.ok(md);
        } else {
            return ResponseEntity.notFound().build();
        }
    
    }

    @PostMapping("/MyList")
    public ResponseEntity<Object> mylist_details(@RequestParam(value="user_id") String user_id){
        System.out.println("fdfdfdfdfdsdfd");
        System.out.println(user_id);
        List<Movie_ID> md= mylistdetailsservice.findByUserID(user_id);
        List<Movie_Details> mld = new ArrayList<Movie_Details>();
        for (int i=0; i<md.size(); i++)
        {
            mld.add(movieDetailsService.findByMovieName(md.get(i).getMovie_name()));
            System.out.println(movieDetailsService.findByMovieName(md.get(i).getMovie_name()).getMovie_name());

        }
        if (mld != null) {
            return ResponseEntity.ok(mld);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/Genres")
    public ResponseEntity<Object> genre_movies(@RequestParam(value="genre") String genre){
        List<Movie_Details> md = movieDetailsService.findbyMovieGenre(genre);
        System.out.println(genre);
        if (md != null) {return ResponseEntity.ok(md);}
        else
            return ResponseEntity.notFound().build();
    }

}

package com.iiitb.spe.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iiitb.spe.service.MovieDetailsService;

import com.iiitb.spe.models.Movie_Details;
@CrossOrigin(origins = "*")

@RestController
public class MovieDetailsController {
    @Autowired
    private MovieDetailsService movieDetailsService;
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
}

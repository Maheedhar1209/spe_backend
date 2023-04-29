package com.iiitb.spe.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.iiitb.spe.models.Movie_ID;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iiitb.spe.service.MovieDetailsService;

import com.iiitb.spe.models.Movie_Details;
@CrossOrigin(origins = "http://localhost:4200")

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
    @GetMapping("/sms")
    public ResponseEntity<Void> sendsms(@RequestParam String movie_details,@RequestParam String phone_number){
        System.out.println("dgdgdgdd");
        final String ACCOUNT_SID = "ACbcec39040fe22ddea4512e79e24b6c90";
        final String AUTH_TOKEN = "39f93be0adddd9475fb38ea1d3636e6b";
        final String FROM_NUMBER = "+16317693117";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        //System.out.println(md.getMovie_name());
        final String to="+91"+phone_number;
        try {
            System.out.println("dgdgdgdd1");
            Message.creator(new PhoneNumber(to), new PhoneNumber(FROM_NUMBER), movie_details).create();
            System.out.println("dgdgdgdd2");
            return ResponseEntity.ok().build();
        }catch (TwilioException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

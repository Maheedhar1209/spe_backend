package com.iiitb.spe.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.iiitb.spe.models.Movie_ID;
import com.iiitb.spe.models.User_Login;
import com.iiitb.spe.models.NewReleases;
import com.iiitb.spe.repositories.MovieDetailsRepository;
import com.iiitb.spe.repositories.NewReleasesRepository;
import com.iiitb.spe.repositories.UserLoginRepository;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iiitb.spe.service.MovieDetailsService;
import com.iiitb.spe.service.MyListDetailsService;

import com.iiitb.spe.models.Movie_Details;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class MovieDetailsController {
    @Autowired
    private MovieDetailsService movieDetailsService;
    @Autowired
    private MyListDetailsService mylistdetailsservice;
    @Autowired
    private NewReleasesRepository newReleasesRepository;
    @Autowired
    private MovieDetailsRepository movieDetailsRepository;
    @Autowired
    private UserLoginRepository userLoginRepository;
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

    @GetMapping("/newreleases")
    public  List<Movie_Details> newreleases(@RequestParam String user_id){
        System.out.println(user_id);
        List<NewReleases> newReleases=newReleasesRepository.findByID(user_id);
        List<Movie_Details> mld = new ArrayList<Movie_Details>();
        for (int i=0; i<newReleases.size(); i++)
        {
            mld.add(movieDetailsService.findByMovieName(newReleases.get(i).getMovie_name()));
            System.out.println(movieDetailsService.findByMovieName(newReleases.get(i).getMovie_name()).getMovie_name());

        }
        return mld;
    }
    @DeleteMapping("/deletenewreleases")
    public ResponseEntity<Void> deletenewreleases(@RequestParam int id){
        newReleasesRepository.deletenewreleases(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/addnewrelease")
    public int  addnewrelease(@RequestBody Movie_Details movie_details){
        Movie_Details md=movieDetailsRepository.findByMovieName(movie_details.getMovie_name());
        if (md!=null)
            return 0;
        movieDetailsRepository.save(movie_details);
        List<User_Login> ul=userLoginRepository.allusers();
        for (User_Login user_login:ul){
            newReleasesRepository.save(new NewReleases(user_login.getPhone_number(),movie_details.getMovie_name()));
        }

        return 1;
    }
    @PostMapping("/changemoviedetails")
    public int  changemoviedetails(@RequestBody Movie_Details movie_details){
        Movie_Details md=movieDetailsRepository.findByMovieName(movie_details.getMovie_name());
        if (md==null)
            return 0;
        md.setMovie_img(movie_details.getMovie_img());
        md.setAbout(movie_details.getAbout());
        md.setAbout(movie_details.getAbout());
        md.setOtt_platforms(movie_details.getOtt_platforms());
        md.setRelease_date(movie_details.getRelease_date());
        movieDetailsRepository.save(md);
        return 1;
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
    @PostMapping("/AddMyList")
    public boolean add_mylist(@RequestParam(value="movie_name") String movie_name, @RequestParam(value="user_id") String user_id){
        System.out.println("adder");
        System.out.println(movie_name);
        mylistdetailsservice.addMovieMyList(movie_name, user_id);
        List<Movie_Details> mld = new ArrayList<Movie_Details>();
//        if (mld != null) {
//            return ResponseEntity.ok(mld);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//        Object str = new Object();
//        str = "hello";
        return true;
    }
}

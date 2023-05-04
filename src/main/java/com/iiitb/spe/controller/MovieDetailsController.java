package com.iiitb.spe.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.iiitb.spe.models.NewReleases;
import com.iiitb.spe.models.User_Login;
import com.iiitb.spe.repositories.MovieDetailsRepository;
import com.iiitb.spe.repositories.NewReleasesRepository;
import com.iiitb.spe.repositories.UserLoginRepository;
import com.twilio.exception.TwilioException;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iiitb.spe.service.MovieDetailsService;

import com.iiitb.spe.models.Movie_Details;

import com.twilio.rest.verify.v2.service.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
@CrossOrigin(origins = "*")

@RestController
public class MovieDetailsController {
    @Autowired
    private MovieDetailsService movieDetailsService;
    @Autowired
    private NewReleasesRepository newReleasesRepository;
    @Autowired
    private MovieDetailsRepository movieDetailsRepository;
    @Autowired
    private UserLoginRepository userLoginRepository;
    @GetMapping("/Movie")
    public Movie_Details moviedetails(@RequestParam("movie_name") String movie_name){
        System.out.println("fdfdfdfdfdsdfd");
        System.out.println(movie_name);
        Movie_Details md=movieDetailsService.findByMovieName(movie_name);

       return md;
    }
    @GetMapping("/newreleases")
    public  List<NewReleases> newreleases(@RequestParam String phone_number){
        System.out.println(phone_number);
        List<NewReleases> newReleases=newReleasesRepository.findbyphonenumber(phone_number);
        return newReleases;
    }
    @DeleteMapping("/deletenewreleases")
    public ResponseEntity<Void> deletenewreleases(@RequestParam String phone_number){
        newReleasesRepository.deletenewreleases(phone_number);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/addnewrelease")
    public ResponseEntity<Void>  addnewrelease(@RequestBody Movie_Details movie_details){
        movieDetailsRepository.save(movie_details);
       List<User_Login> ul=userLoginRepository.allusers();
       for (User_Login user_login:ul){
           newReleasesRepository.save(new NewReleases(user_login.getPhone_number(),movie_details.getMovie_name()));
       }
       return ResponseEntity.ok().build();
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

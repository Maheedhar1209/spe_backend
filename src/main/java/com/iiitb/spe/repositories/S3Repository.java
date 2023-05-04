package com.iiitb.spe.repositories;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class S3Repository {
    @Autowired
    private final AmazonS3Client s3Client; //.config file setup

    public S3Repository(AmazonS3Client s3Client)
    {
        this.s3Client = s3Client;
    }
    @GetMapping("/MovieTitle")
    public Object getURL(){
        Date date = new Date(new Date().getTime() + 1000 * 1000);
        return this.s3Client.generatePresignedUrl(
                "movietitles",
                "movie4.jpeg",
                date // S3 default is 900 seconds (15 minutes)
        );
    }
}

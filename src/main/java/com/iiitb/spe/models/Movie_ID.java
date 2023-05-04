package com.iiitb.spe.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "mylist_details")
public class Movie_ID {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "movie_name")
    private String movie_name;

    @Column(name = "user_id")
    private String user_id;

    public int getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}


package com.iiitb.spe.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiitb.spe.models.Movie_Details;

import java.util.List;
@Repository
public interface MovieDetailsRepository extends JpaRepository<Movie_Details, String> {


    @Query(value = "SELECT * FROM movie_details m WHERE m.movie_name = :movie_name", nativeQuery = true)
    Movie_Details findByMovieName(@Param("movie_name") String movie_name);

}

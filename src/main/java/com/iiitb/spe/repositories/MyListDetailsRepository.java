package com.iiitb.spe.repositories;



import com.iiitb.spe.models.Movie_ID;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiitb.spe.models.Movie_Details;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface MyListDetailsRepository extends JpaRepository<Movie_ID, Integer> {

    @Query(value = "SELECT * FROM mylist_details m WHERE m.user_id = :user_id", nativeQuery = true)
    List<Movie_ID> findByUserID(@Param("user_id") String user_id);

    @Modifying
    @Query(value = "INSERT INTO mylist_details(user_id, movie_name) VALUES (:user_id, :movie_name);", nativeQuery = true)
    void addMovieMyList(@Param(value = "movie_name") String movie_details, @Param(value = "user_id") String user_id);
}

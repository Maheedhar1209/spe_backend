package com.iiitb.spe.repositories;



import com.iiitb.spe.models.Movie_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiitb.spe.models.Movie_Details;

import java.util.List;

@Repository
public interface MyListDetailsRepository extends JpaRepository<Movie_ID, Integer>{

    @Query(value = "SELECT * FROM mylist_details m WHERE m.user_id = :user_id", nativeQuery = true)
    List<Movie_ID> findByUserID(@Param("user_id") String user_id);
}

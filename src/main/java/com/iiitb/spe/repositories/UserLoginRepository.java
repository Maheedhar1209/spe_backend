package com.iiitb.spe.repositories;



import com.iiitb.spe.models.User_Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiitb.spe.models.Movie_Details;

import java.util.List;
@Repository
public interface UserLoginRepository extends JpaRepository<User_Login, String> {


    @Query(value = "Select * from user_login",nativeQuery = true)
    List<User_Login> allusers();

}

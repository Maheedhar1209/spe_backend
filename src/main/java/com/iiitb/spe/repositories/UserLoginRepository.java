package com.iiitb.spe.repositories;



import com.iiitb.spe.models.User_Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiitb.spe.models.Movie_Details;

import java.util.List;
@Repository
public interface UserLoginRepository extends JpaRepository<User_Login, String> {


    @Query(value = "Select * from user_login",nativeQuery = true)
    List<User_Login> allusers();

    @Query(value = "Select user_login.id, user_login.phone_number from user_login where user_login.phone_number=:phone", nativeQuery = true)
    User_Login getIDbyPhone(@Param(value = "phone") String phone);

    @Modifying
    @Query(value ="Insert into user_login(phone_number) values(:phone)", nativeQuery = true)
    void addUser(@Param(value= "phone") String phone);

}

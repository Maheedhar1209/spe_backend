package com.iiitb.spe.repositories;



import com.iiitb.spe.models.NewReleases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


import com.iiitb.spe.models.Movie_Details;

import java.util.List;
@Repository
public interface NewReleasesRepository extends JpaRepository<NewReleases, String> {


    @Query(value = "SELECT * FROM new_releases m WHERE m.id = :id", nativeQuery = true)
    List<NewReleases> findByID(@Param("id") String id);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM new_releases WHERE id = :id", nativeQuery = true)
    void  deletenewreleases(@Param("id") int id);

}

package com.iiitb.spe.service;


import javax.transaction.Transactional;

import com.iiitb.spe.models.Movie_ID;
import com.iiitb.spe.repositories.MyListDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class MyListDetailsService {
    @Autowired
    private MyListDetailsRepository repo;


    public List<Movie_ID> findByUserID(String user_id)
    {
        return repo.findByUserID(user_id);
    }
}
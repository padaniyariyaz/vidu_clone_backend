package com.example.vidu_clone.services;


import com.example.vidu_clone.models.hero_section;
import com.example.vidu_clone.models.hero_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Hero_section_service {

    @Autowired
    private hero_repository repo;


    public List<hero_section> getbannerfromrepo(){
        return repo.findAll();
    }


    public void insertbannerintorepo(hero_section data){

        repo.insert(data);
    }




}


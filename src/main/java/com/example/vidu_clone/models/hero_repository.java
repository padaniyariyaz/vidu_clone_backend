package com.example.vidu_clone.models;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface hero_repository extends MongoRepository<hero_section,String> {
}

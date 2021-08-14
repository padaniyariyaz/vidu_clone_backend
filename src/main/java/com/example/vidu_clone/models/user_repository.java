package com.example.vidu_clone.models;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface user_repository extends MongoRepository<users,String> {

    users findByUsername(String username);
}

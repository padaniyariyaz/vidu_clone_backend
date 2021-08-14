package com.example.vidu_clone.services;


import com.example.vidu_clone.models.movies_repository;
import com.example.vidu_clone.models.my_movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;


import java.util.List;

import java.util.Optional;

@Service
public class Movie_Service {


    @Autowired
    private movies_repository moviesrepos;


    @Autowired
    private MongoTemplate Mongos;


    public List<my_movies> getmovies() {
        return moviesrepos.findAll();
    }

    public Optional<my_movies> getmovieId(String mv) throws Exception {
        Optional<my_movies> movie = moviesrepos.findById(mv);

        if (!movie.isPresent()) {
            throw new Exception("Movie with id " + mv + " Not Found");
        }
        return movie;

    }

    public void createmovie(my_movies mv) {
        moviesrepos.insert(mv);
    }

    public void deleteMovie(String id) {
        moviesrepos.deleteById(id);


    }

    public List<my_movies> searchbyname(String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(title));
        List<my_movies> my_movies = Mongos.find(query, my_movies.class);
        return my_movies;
    }

    public List<my_movies> featured(boolean value) {
        Query query = new Query();
        query.addCriteria(Criteria.where("featured").is(value));
        List<my_movies> my_movies = Mongos.find(query, my_movies.class);
        return my_movies;
    }

    public my_movies updatemoviess(my_movies movie, String id) {

        Optional<my_movies> searchit = moviesrepos.findById(id);

        searchit.get().setPurchase(movie.getPurchase());
        searchit.get().setDescription(movie.getDescription());
        searchit.get().setFeatured(movie.getFeatured());
        searchit.get().setImg_name(movie.getImg_name());
        searchit.get().setRating(movie.getRating());
        searchit.get().setName(movie.getName());
        searchit.get().setPoster_name(movie.getPoster_name());
        searchit.get().setRent(movie.getRent());

        my_movies updatedone = moviesrepos.save(searchit.get());
        return updatedone;


    }
}

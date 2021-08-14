package com.example.vidu_clone.services;

import com.example.vidu_clone.models.tv_shows;
import com.example.vidu_clone.models.tv_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TV_Service {

    @Autowired
    private tv_repository tvrepository;

    @Autowired
    private MongoTemplate Mongos;

    public List<tv_shows> gettv() {
        return tvrepository.findAll();
    }

    public void inserttv(tv_shows tvdata) {
        tvrepository.insert(tvdata);
    }

    public Optional<tv_shows> gettvId(String id) throws Exception {
        Optional<tv_shows> tvfound = tvrepository.findById(id);
        if (!tvfound.isPresent()) {
            throw new Exception("TV with id " + id + " is not Found");
        }
        return tvfound;

    }

    public tv_shows updatetv(tv_shows tv, String id) {

        Optional<tv_shows> updatetvshow = tvrepository.findById(id);
        updatetvshow.get().setRating(tv.getRating());
        updatetvshow.get().setName(tv.getName());
        updatetvshow.get().setPoster_name(tv.getPoster_name());
        updatetvshow.get().setRent(tv.getRent());
        updatetvshow.get().setPurchase(tv.getPurchase());
        updatetvshow.get().setDescription(tv.getDescription());
        updatetvshow.get().setFeatured(tv.getFeatured());
        updatetvshow.get().setImg_name(tv.getImg_name());
        tv_shows updated = tvrepository.save(updatetvshow.get());
        return updated;


    }

    public void deletetv(String id) {
        tvrepository.deleteById(id);
    }

    public List<tv_shows> searchbyname(String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(title));
        List<tv_shows> tv_shows = Mongos.find(query, tv_shows.class);
        return tv_shows;
    }

    public List<tv_shows> featured(boolean value) {
        Query query = new Query();
        query.addCriteria(Criteria.where("featured").is(value));
        List<tv_shows> tv_shows = Mongos.find(query, tv_shows.class);
        return tv_shows;
    }


}


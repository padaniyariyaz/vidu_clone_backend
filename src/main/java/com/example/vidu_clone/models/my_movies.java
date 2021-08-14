package com.example.vidu_clone.models;

import org.springframework.data.annotation.Id;

import java.util.Locale;

public class my_movies {
    @Id
    private String id;
    private String name;
    private String description;
    private String img_name;
    private String poster_name;
    private String rating;
    private float rent;
    private float purchase;
    private String featured;

    public my_movies() {
    }

    public my_movies(String id, String name, String description, String img_name, String poster_name, String rating, float rent, float purchase, String featured) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img_name = img_name;
        this.poster_name = poster_name;
        this.rating = rating;
        this.rent = rent;
        this.purchase = purchase;
        this.featured = featured;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImg_name() {
        return img_name;
    }

    public String getPoster_name() {
        return poster_name;
    }

    public String getRating() {
        return rating;
    }

    public float getRent() {
        return rent;
    }

    public float getPurchase() {
        return purchase;
    }

    public String getFeatured() {
        return featured;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public void setPoster_name(String poster_name) {
        this.poster_name = poster_name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRent(float rent) {
        this.rent = rent;
    }

    public void setPurchase(float purchase) {
        this.purchase = purchase;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }
}

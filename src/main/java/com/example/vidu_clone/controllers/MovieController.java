package com.example.vidu_clone.controllers;

import com.example.vidu_clone.models.*;
import com.example.vidu_clone.services.Movie_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin("https://vidu-clone.herokuapp.com/")
@RestController

public class MovieController {


    @Autowired
    private Movie_Service Movie_Service;



    @GetMapping("/movies")
    public ResponseEntity getmovies() {

        CustomizedResponse custom = new CustomizedResponse("Movies", Movie_Service.getmovies());
        return new ResponseEntity(custom, HttpStatus.OK);

    }


    @PostMapping(value = "/movies", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createmovies(@RequestBody my_movies mv) {
        Movie_Service.createmovie(mv);
        return new ResponseEntity(HttpStatus.OK);

    }


    @GetMapping("/movies/{id}")
    public ResponseEntity getmovieid(@PathVariable("id") String id) {
        CustomizedResponse custom = null;
        try {
            custom = new CustomizedResponse("Found the Movie", Collections.singletonList(Movie_Service.getmovieId(id)));
        } catch (Exception e) {
            custom = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(custom, HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity(custom, HttpStatus.OK);
    }




    @PutMapping(value = "/movies/put/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updatemovies(@RequestBody my_movies mv, @PathVariable("id") String id) {
        CustomizedResponse custom = new CustomizedResponse("Updated Movie " + mv.getName(), Collections.singletonList(Movie_Service.updatemoviess(mv, id)));
        Movie_Service.updatemoviess(mv, id);

        return new ResponseEntity(custom, HttpStatus.OK);
    }


    @DeleteMapping("/movies/{id}")
    public ResponseEntity deletemovie(@PathVariable("id") String id) {

        Movie_Service.deleteMovie(id);
        return new ResponseEntity(HttpStatus.OK);


    }


    @GetMapping(value = "/movie")
    public ResponseEntity searchmoviename(@RequestParam("name") String title)
    {
        CustomizedResponse custom = new CustomizedResponse("Matched String "+title, Movie_Service.searchbyname(title));
        return new ResponseEntity(custom,HttpStatus.OK);
    }

    @GetMapping(value = "/moviesfeatured")
    public ResponseEntity searchmoviefeatured(@RequestParam("featured") boolean title)
    {
        CustomizedResponse custom = new CustomizedResponse("Matched String "+title, Movie_Service.featured(title));
        return new ResponseEntity(custom,HttpStatus.OK);
    }


}

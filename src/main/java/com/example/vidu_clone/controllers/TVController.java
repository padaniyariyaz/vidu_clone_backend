package com.example.vidu_clone.controllers;


import com.example.vidu_clone.models.CustomizedResponse;
import com.example.vidu_clone.models.tv_shows;
import com.example.vidu_clone.services.TV_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
@CrossOrigin("https://vidu-clone1.herokuapp.com/")
@Controller
public class TVController {

    @Autowired
    private TV_Service tvservices;



    @GetMapping("/tv_shows")
    public ResponseEntity gettv() {
        CustomizedResponse c = new CustomizedResponse("List of TV", tvservices.gettv());
        return new ResponseEntity(c, HttpStatus.OK);
    }



    @GetMapping("/tv_shows/{id}")
    public ResponseEntity gettvid(@PathVariable("id") String id) {
        CustomizedResponse custom = null;
        try {
            custom = new CustomizedResponse("Found the TV", Collections.singletonList(tvservices.gettvId(id)));
        } catch (Exception e) {
            custom = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(custom, HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity(custom, HttpStatus.OK);
    }



    @PostMapping(value = "/tv_shows", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createtv(@RequestBody tv_shows tvdata) {
        tvservices.inserttv(tvdata);
        return new ResponseEntity(HttpStatus.OK);
    }



    @PutMapping(value = "/tv_shows/put/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updatetv(@RequestBody tv_shows tv, @PathVariable("id") String id) {

        CustomizedResponse custom = new CustomizedResponse("Update data of TV " + tv.getName(), Collections.singletonList(tvservices.updatetv(tv, id)));


        return new ResponseEntity(custom, HttpStatus.OK);
    }


    @DeleteMapping("/tv_shows/{id}")
    public ResponseEntity deletetv(@PathVariable("id") String id) {
        tvservices.deletetv(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "/tv_show")
    public ResponseEntity searchtvname(@RequestParam("name") String title)
    {
        CustomizedResponse custom = new CustomizedResponse("Matched String "+title, tvservices.searchbyname(title));
        return new ResponseEntity(custom,HttpStatus.OK);
    }

    @GetMapping(value = "/tv_showsfeatured")
    public ResponseEntity searchtvfeatured(@RequestParam("featured") boolean title)
    {
        CustomizedResponse custom = new CustomizedResponse("Matched String "+title, tvservices.featured(title));
        return new ResponseEntity(custom,HttpStatus.OK);
    }





}

package com.example.vidu_clone.controllers;

import com.example.vidu_clone.models.CustomizedResponse;
import com.example.vidu_clone.models.users;
import com.example.vidu_clone.services.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin("https://vidu-clone.herokuapp.com/")
@Controller
public class UserController {


    @Autowired
    private User_Service usrservice;


    @GetMapping("/users")
    public ResponseEntity getusers()
    {   CustomizedResponse custom = new CustomizedResponse("List of Users",usrservice.getAll());

        return new ResponseEntity(custom,HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getuserid(@PathVariable("id") String id)
    {
        CustomizedResponse custom = null;
        try {
            custom= new CustomizedResponse("Found the User", Collections.singletonList(usrservice.getuser(id)));
        } catch (Exception e) {
            custom = new CustomizedResponse("Not found the user",null);
            return new ResponseEntity(custom, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(custom,HttpStatus.OK);
    }

    @PostMapping(value = "/users",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createuser(@RequestBody users usr)
    {   usrservice.insertUser(usr);
        CustomizedResponse custom = new CustomizedResponse("Added User:  "+usr.getFirstname(),null);
        return new ResponseEntity(custom,HttpStatus.OK);

    }
}


package com.example.vidu_clone.controllers;


import com.example.vidu_clone.models.CustomizedResponse;
import com.example.vidu_clone.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://vidu-clone.herokuapp.com/")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity userlogin(@RequestBody users usr)
    {
        CustomizedResponse c = null;
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usr.getUsername(),usr.getPassword()));
            CustomizedResponse response = new CustomizedResponse("You Have Logged in",null);
            return new ResponseEntity(response,HttpStatus.OK);

        } catch (BadCredentialsException e) {
            CustomizedResponse response = new CustomizedResponse("Username or Password is Wrong",null);
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }

    }




}

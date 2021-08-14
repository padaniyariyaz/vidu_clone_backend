package com.example.vidu_clone.services;


import com.example.vidu_clone.models.user_repository;
import com.example.vidu_clone.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class User_Service implements UserDetailsService {

    @Autowired
    private user_repository usrepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public void insertUser(users usr)
    {
        String newpass = passwordEncoder.encode(usr.getPassword());
        usr.setPassword(newpass);

        usrepo.insert(usr);
    }

    public Optional<users> getuser(String id) throws Exception {
        Optional<users> userfound = usrepo.findById(id);
        if(!userfound.isPresent())
        {
            throw new Exception ("User "+id+" is Not Found");
        }
        return userfound;
    }

    public List<users> getAll()
    {
        return usrepo.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        users founduser = usrepo.findByUsername(username);

        String uname = founduser.getUsername();
        String password = founduser.getPassword();

        return new User(uname,password, new ArrayList<>());
    }
}


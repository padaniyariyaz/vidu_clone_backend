package com.example.vidu_clone.configuration;

import com.example.vidu_clone.services.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class Securityconfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private User_Service usrservice;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usrservice);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/auth").permitAll()
                .antMatchers("/movies/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/tv_show/**").permitAll()
                .antMatchers("/tv_showsfeatured/**").permitAll()
                .antMatchers("/moviesfeatured/**").permitAll()
                .anyRequest().authenticated();

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}

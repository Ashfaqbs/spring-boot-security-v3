package com.ashfaq.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.example.dao.UserRepository;
import com.ashfaq.example.model.AppUser;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //this api should be accessable to all 
    @PostMapping("/register/user")
    public AppUser createUser(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser appUser = myUserRepository.save(user);
        return appUser;
    }
    
    
    
}
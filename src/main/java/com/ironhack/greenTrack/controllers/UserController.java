package com.ironhack.greenTrack.controllers;


import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userRepository.findAll();

        //servicios
        //return serviceUsers.getAll();
    }

    @PostMapping("/create-user")
    @ResponseStatus (HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


}

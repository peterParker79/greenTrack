package com.ironhack.greenTrack.controllers;


import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.repositories.UserRepository;
import com.ironhack.greenTrack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    UserService userService;

//    @GetMapping("{id}")
//    public User getUser(@PathVariable int id) {
//        return userService.getUserbyId(id);
//    }

    //sólo pueden ver los perfiles los administradores
    @GetMapping("/profiles/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserbyId(id);
    }



    @GetMapping("/profiles")
    @ResponseStatus(HttpStatus.OK)

    public List<User> getUsers() {
        return userService.getUsers();
    }

    //Un administrador puede crear usuarios con
    //perfil de administrador
    @PostMapping("/profiles/create-user")
    @ResponseStatus (HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

}

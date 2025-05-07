package com.ironhack.greenTrack.controllers;

import com.ironhack.greenTrack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")


public class PublicController {

    @Autowired
    UserService userService;
    @GetMapping
    @ResponseStatus
    // de manera pública se puede consultar el número de usuarios registrados en la app

    public long usersRegistered() {return userService.count();}

}

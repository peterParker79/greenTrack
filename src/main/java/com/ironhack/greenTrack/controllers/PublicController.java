package com.ironhack.greenTrack.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")


public class PublicController {

    @GetMapping
    @ResponseStatus
    // de manera pública se puede consultar el número de usuarios registrados en la app
    public String usersRegistered() {
        // serviceUsers.getNumUsers()
    }
}

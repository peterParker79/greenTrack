package com.ironhack.greenTrack.controllers;

import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")

public class PublicController {

    @Autowired
    UserService userService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    // de manera pública se puede consultar el número de usuarios registrados en la app



    public String  usersRegistered() {
        return "Wellcome GREEN TRACK!!!\nCurrently there are "+
                userService.count() +" users improving their green impact!" +
                "Please register now through /api/public/register\n\n\tGreen Growing!\n";

    }

    @PostMapping("/register")
    @ResponseStatus (HttpStatus.CREATED)
    public String createUser(@RequestBody User user) {
        // en el registro el usuario tiempre tendrá rol de user
        //sólo proporciona nombre y password
        user.setRole(ERole.ROLE_USER);
        return
                "Welcome " + user.getName() +"!!"+
                        "\n Has been registered " + userService.save(user);
    }
}

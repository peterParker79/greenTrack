package com.ironhack.greenTrack.controllers;


import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.repositories.UserRepository;
import com.ironhack.greenTrack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

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
//    @GetMapping("/profiles/{id}")
//    public User getUser(@PathVariable int id) {
//        return userService.getUserbyId(id);
//
//    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<User> accessById(@PathVariable int id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean isAdmin = authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        User user = userService.getUserbyId(id);
        if (username.toLowerCase().equals(user.getName().toLowerCase())  ||
            isAdmin)
         {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/profiles")
    @ResponseStatus(HttpStatus.OK)

    public List<User> getUsers() {
        return userService.getUsers();
    }

    //Un administrador puede crear usuarios con
    //perfil de administrador
    @Secured("ROLE_ADMIN")

    @PostMapping("/profiles/create-user")
    @ResponseStatus (HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

}

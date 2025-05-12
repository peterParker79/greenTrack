package com.ironhack.greenTrack.controllers;


import com.ironhack.greenTrack.models.*;
import com.ironhack.greenTrack.repositories.EcoActionRepository;
import com.ironhack.greenTrack.repositories.UserRepository;
import com.ironhack.greenTrack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    UserRepository userRepository;

    @Autowired
    EcoActionRepository ecoActionRepository;

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

    @DeleteMapping("/profiles/delete-user/{id}")
    @ResponseStatus (HttpStatus.CREATED)
    public void deleteUser(@PathVariable int id) {
         userService.deleteUser(id);
    }


    @PostMapping ("/profiles/{id}/new-ecoaction/to-cycle")
    @ResponseStatus (HttpStatus.CREATED)
    public ToCycle addEcoActionToUser(@PathVariable int id, @RequestBody ToCycleDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ToCycle ecoAction = new ToCycle();
        ecoAction.setDate(dto.getDate());
        ecoAction.setDescription(dto.getDescription()); //personalizada del usuario
        ecoAction.setKilometers(dto.getKilometers());
        ecoAction.setOrigin(dto.getOrigin());
        ecoAction.setDestination(dto.getDestination());

        int greenImpact = Math.min(9, Math.max(1, dto.getKilometers() / 3)); //1 punto cada 3 km
        ecoAction.setGreenImpact(greenImpact);


        ecoAction.setUser(user);

        return ecoActionRepository.save(ecoAction);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/api/profiles/{id}/update-name")
    public ResponseEntity<UpdateNameDTO> updateUserName(@PathVariable int id, @RequestBody UpdateNameDTO userRenamed) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User profile not found"));

        user.setName(userRenamed.getNewName());
        userService.save(user);
        ResponseEntity <UpdateNameDTO> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;



    }







}


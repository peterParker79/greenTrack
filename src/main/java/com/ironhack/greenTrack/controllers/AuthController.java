package com.ironhack.greenTrack.controllers;

import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.services.JwtService;
import com.ironhack.greenTrack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
       return jwtService.login(user);
    }

    // ME HE LLEVADO ESTO A JWTSERVICE
//    public ResponseEntity<String> login(@RequestBody User user) {
//        // si el usuario existe lo guarda, si no se lanza una excepción
//        User userLogin = userService.getUserName(user.getName()); //recuperado de la BBDD
//
//        boolean validPassword = userService.verifyPassword(user.getPassword(), userLogin); //en body viene en texto plano
//
//        if (validPassword) {
//           boolean validRole=false;
//            String roleUser = userLogin.getRole().name();
//            for (ERole role : ERole.values()) {
//               if( role.name().toUpperCase().equals(roleUser)){validRole=true;}
//            }
//
//            if (validRole){
//            String token = jwtService.generateToken(userLogin.getName(), roleUser );
//            return ResponseEntity.ok(token);}
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
//    }

}

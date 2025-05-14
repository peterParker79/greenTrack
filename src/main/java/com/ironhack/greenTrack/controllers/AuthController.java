package com.ironhack.greenTrack.controllers;

import com.ironhack.greenTrack.models.AuthResponseDTO;
import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.models.UserLoginDTO;
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
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserLoginDTO userDTO) {
       return jwtService.login(userDTO);
    }
}

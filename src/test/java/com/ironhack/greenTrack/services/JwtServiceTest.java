package com.ironhack.greenTrack.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JwtServiceTest {

    @Autowired JwtService jwtService;

    @Test
    @DisplayName("Check generate Token")
    void generateToken() {
        String generatedToken= jwtService.generateToken(5, "Dani","ROLE_ADMIN");
        System.out.println("TOKEN" + generatedToken);
    }


}
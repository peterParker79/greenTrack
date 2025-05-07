package com.ironhack.greenTrack.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service


public class JwtService {
    private static final String SECRET = "secret";


    public String generateToken(String userName, String role) {
        return JWT.create()
                .withSubject (userName)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
                .sign(Algorithm.HMAC256(SECRET));
    }

    //public boolen validateToken (String token){}

}

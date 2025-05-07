package com.ironhack.greenTrack.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service


public class JwtService {
    @Autowired
    UserService userService;

    private static final String SECRET = "secret";


    public String generateToken(String userName, String role) {
        return JWT.create()
                .withSubject (userName)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }


    public String extractUsername(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getSubject();
    }


    public String extractRoles(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);

        return jwt.getClaim("roles").asString();
    }

    public ResponseEntity<String> login( User user) {
        // si el usuario existe lo guarda, si no se lanza una excepción
        User userLogin = userService.getUserName(user.getName()); //recuperado de la BBDD

        boolean validPassword = userService.verifyPassword(user.getPassword(), userLogin); //en body viene en texto plano

        if (validPassword) {
            boolean validRole=false;
            String roleUser = userLogin.getRole().name();
            for (ERole role : ERole.values()) {
                if( role.name().toUpperCase().equals(roleUser.toUpperCase())){validRole=true;}
            }

            if (validRole){
                String token = generateToken(userLogin.getName(), roleUser );
                return ResponseEntity.ok(token);}
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
    }

}

package com.ironhack.greenTrack.filters;

import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {


        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); //siguiente filtro si autenticación no ok
            return;
        }


        String token = header.replace("Bearer ", "");

        boolean tokenValid = jwtService.validateToken(token);
        if (!tokenValid) {
            filterChain.doFilter(request, response);// pasa al siguiente filtro
            return;
        }

        String userName = jwtService.extractUsername(token);
        String role = jwtService.extractRole(token);


        List<String> roles = ERole.toList();
    }

//    private Collection<GrantedAuthority> extractAuthorities(String rolesString) {
//        // Process the roles string. Example: "[ROLE_ADMIN, ROLE_USER]"
//        if (rolesString == null || rolesString.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        // Remove brackets and split by commas
//        String roles = rolesString.replace("[", "").replace("]", "");
//        String[] roleArray = roles.split(",");
//
//        return Arrays.stream(roleArray)
//                .map(String::trim) // Remove spaces
//                .map(SimpleGrantedAuthority::new) // We'll have roles in the correct format for Spring Security to recognize them
//                .collect(Collectors.toList());
//    }



}

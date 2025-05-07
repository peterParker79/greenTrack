package com.ironhack.greenTrack.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfigbackup {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf->csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                       // .requestMatchers("/api/**").permitAll()
//                       // .anyRequest().authenticated()
//                       // .requestMatchers("api/users").authenticated()
//                        .anyRequest().permitAll()
//                );
//              //  .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//
//
//}

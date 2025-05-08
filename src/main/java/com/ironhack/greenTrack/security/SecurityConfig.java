package com.ironhack.greenTrack.security;


import com.ironhack.greenTrack.filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity (securedEnabled = true) // @Secured @PreAuthorize
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // For development. In production, configure appropriately
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Server doesn't store session state, because it's a REST API
                .authorizeHttpRequests(auth -> auth
                        // Public routes
                        //.anyRequest().permitAll()
                        .requestMatchers("/api/public/register").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/public/**").permitAll()

                        // Routes protected by role
                        .requestMatchers("/api/profiles/").hasRole("ADMIN")
                        // All other routes require authentication
                        .anyRequest().authenticated()
                )
                // Add our filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}











//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf->csrf.disable())
//
//                .authorizeHttpRequests(auth -> auth
//                       // .requestMatchers("/api/**").permitAll()
//                       // .anyRequest().authenticated()
//                       // .requestMatchers("api/users").authenticated()
//                        .anyRequest().permitAll()
//                );
//              //  .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }





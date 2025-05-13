package com.ironhack.greenTrack.security;


import com.ironhack.greenTrack.filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
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
//@EnableMethodSecurity (securedEnabled = true) // @Secured @PreAuthorize
@Profile("!test")
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // For development. In production, configure appropriately
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Server doesn't store session state, because it's a REST API

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) //added

                .authorizeHttpRequests(auth -> auth
                        // Public routes
                        //.anyRequest().permitAll()
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()


                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/public/**").permitAll()


                        // Routes protected by role
                        //.requestMatchers("/api/profiles/create-user").hasRole("ADMIN")
                        .requestMatchers("/api/profiles").hasRole("ADMIN") //muestra TODOS los perfiles

                        //.requestMatchers("/api/profiles/*").hasRole("ADMIN") //muestra PERFIL POR ID
                        .requestMatchers("/api/profiles/create-user/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/profiles/*").authenticated()
                        .requestMatchers("/api/eco-action/create/**").hasRole("ADMIN")


                        // All other routes require authentication
                        .requestMatchers("/api/profiles/*/update-name").authenticated()
                        .requestMatchers("/api/profiles/*/new-ecoaction/to-cycle").authenticated()

                        //.requestMatchers("/api/profiles/*").authenticated() //muestra PERFIL POR ID
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





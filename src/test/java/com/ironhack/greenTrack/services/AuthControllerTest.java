package com.ironhack.greenTrack.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.greenTrack.controllers.AuthController;
import com.ironhack.greenTrack.models.AuthResponseDTO;
import com.ironhack.greenTrack.models.ERole;
import com.ironhack.greenTrack.models.User;
import com.ironhack.greenTrack.models.UserLoginDTO;
import com.ironhack.greenTrack.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

// INCORRECTO para MockMvc:



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerTest {

    @Autowired
    AuthController authController;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    private User userTest;

/*
    @BeforeEach
    void setUp() {
        userTest = new User();
        userTest.setName("usuarioTesting");
        userTest.setPassword("1234");
        userTest.setRole(ERole.ROLE_USER);
        userService.save(userTest);
        //userRepository.save(userTest);
    }

 */
/*
    @AfterEach
    void tearDown() {
        userRepository.delete(user);
    }
*/

    @Test
    @DisplayName("Test login ok 200")

    public void Login200WithBBDDTest() throws Exception {
        // DTO con credenciales válidas
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setName("Daniel Molto"); //Usuario existente en la BBDD
        loginDTO.setPassword("1234");

        // DTO a JSON
        String json = objectMapper.writeValueAsString(loginDTO);

        // Ejecutamos la petición POST al endpoint de login
        mockMvc.perform(post(URI.create("/api/auth/login"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()); // Solo verificamos que devuelva 200 OK
    }





}

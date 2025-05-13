package com.ironhack.greenTrack.controllers;

import com.ironhack.greenTrack.services.JwtService;
import com.ironhack.greenTrack.services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PublicController.class)
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)





class PublicControllerMockMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @MockitoBean
    private JwtService jwtService;


    @Test

    void usersRegisteredTest() throws Exception {
        Mockito.when(userService.count()).thenReturn(3L + 3L); //simulo 3+3 usuarios registrados 3 de prueba y 3 en BBDD
        Mockito.when(jwtService.validateToken(anyString())).thenReturn(true);
        Mockito.when(jwtService.extractId(anyString())).thenReturn(1);
        mockMvc.perform(get("/api/public"))
                .andExpect(status().isOk());
              //  .andExpect(content().string(Matchers.containsString("Currently there are 3+3 users"))


    }


//    @Test
//    void createUser() {
//    }


}


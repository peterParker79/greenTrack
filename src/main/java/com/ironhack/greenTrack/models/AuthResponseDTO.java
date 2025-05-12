package com.ironhack.greenTrack.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Objeto para respuestas personalizadas
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class AuthResponseDTO {
        private String token;
        private String username;
        private String role;


       public String toString(){
            return
                    " {\ntoken: " + token +
                            "\nusername: " + username +
                            "\nrole: " + role + "\n}";

        }
    }

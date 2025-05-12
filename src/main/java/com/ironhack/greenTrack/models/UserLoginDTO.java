package com.ironhack.greenTrack.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class UserLoginDTO {
        private String name;
    private String password;

}

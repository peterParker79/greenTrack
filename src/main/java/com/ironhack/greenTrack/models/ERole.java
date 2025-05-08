package com.ironhack.greenTrack.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ERole {
    ROLE_USER,
    ROLE_ADMIN;


    @JsonCreator //deserializar
    public static ERole fromString(String valueFromBody) {
        return ERole.valueOf(valueFromBody.toUpperCase());
    }

    @JsonValue// serializar
    public String toJson(){
        return this.name().toLowerCase();
    }

    public static List<String> toList() {
        List<String> roleNames = Arrays.stream(ERole.values())
                .map(ERole::name)
                .toList();
        return roleNames;
    }

}

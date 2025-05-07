package com.ironhack.greenTrack.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

}

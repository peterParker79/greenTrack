package com.ironhack.greenTrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToCycleDTO {
    private String date;
    private String description;
    private int greenImpact;
    private int kilometers;
    private String origin;
    private String destination;


}

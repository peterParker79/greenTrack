package com.ironhack.greenTrack.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cycle")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ToCycle extends EcoAction{
    private int km;
    private String origin;
    private String destination;

}

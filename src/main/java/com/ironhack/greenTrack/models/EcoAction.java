package com.ironhack.greenTrack.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="EcoAction")
@Data
@AllArgsConstructor
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EcoAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String date;

    private String description;

    @Max(value=9)
    @Min(value=1)
    @Digits(integer=1, fraction=0)
    private int greenImpact;

    //cada acción ecológica un único usuario asociado
    @ManyToOne(fetch = FetchType.EAGER) //carga su user asociado
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}


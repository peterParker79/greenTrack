package com.ironhack.greenTrack.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @NotBlank (message="User name can not be avoid.")
        private String name;

        @NotBlank (message="Password can not be avoid.")
        private String password;

        @NotNull (message="Role can not be avoid.")
        @Enumerated(EnumType.STRING)
        private ERole role;

        @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
        private Collection<EcoAction> ecoActions;
}

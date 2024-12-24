package com.tomangg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "weapon1")
    @JsonIgnore
    private List<User> usersAsWeapon1;

    @OneToMany(mappedBy = "weapon2")
    @JsonIgnore
    private List<User> usersAsWeapon2;
}
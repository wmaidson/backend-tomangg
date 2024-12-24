package com.tomangg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate = new Date();

    @Column(unique = true, nullable = false)

    private String username;

    @Column(nullable = false)

    private String password;

    @Column(nullable = false)
    private String roles = "common";

    private int dkp = 0;

    private int guildBoss = 0;

    private int eventQuantity = 0;

    @ManyToOne
    @JoinColumn(name = "weapon1_id")
    private Weapon weapon1;

    @ManyToOne
    @JoinColumn(name = "weapon2_id")
    private Weapon weapon2;

}

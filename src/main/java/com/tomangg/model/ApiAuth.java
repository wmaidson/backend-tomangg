package com.tomangg.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ApiAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String role = "USER";
}

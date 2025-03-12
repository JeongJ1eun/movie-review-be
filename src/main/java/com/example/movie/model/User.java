package com.example.movie.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String username;
    @Enumerated(EnumType.STRING)
    private Provider provider;
    private String providerId;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}

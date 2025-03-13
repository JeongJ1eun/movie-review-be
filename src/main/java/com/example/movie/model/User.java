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
    
    @Column(length = 300, nullable = true)
    private String bio;
    
    @Column(nullable = true)
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE; // 사용자 상태

    public enum UserStatus {
        ACTIVE,
        BLOCKED
    }

    public void block() {
        this.status = UserStatus.BLOCKED;
    }

    public void unblock() {
        this.status = UserStatus.ACTIVE;
    }

    public boolean isBlocked() {
        return this.status == UserStatus.BLOCKED;
    }
}

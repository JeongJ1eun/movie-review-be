package com.example.movie.dto;

import com.example.movie.model.User;
import lombok.Data;

@Data
public class UserProfileDto {
    private String email;
    private String username;
    private String bio;
    private String profileImageUrl;

    public static UserProfileDto from(User user) {
        UserProfileDto dto = new UserProfileDto();
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setBio(user.getBio());
        dto.setProfileImageUrl(user.getProfileImageUrl());
        return dto;
    }
} 
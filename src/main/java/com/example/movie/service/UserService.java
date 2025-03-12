package com.example.movie.service;

import com.example.movie.dto.UserProfileDto;
import com.example.movie.dto.UserProfileUpdateDto;
import com.example.movie.model.User;
import com.example.movie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserProfileDto getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return UserProfileDto.from(user);
    }

    @Transactional
    public void updateProfile(String email, UserProfileUpdateDto profileUpdateDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        user.setBio(profileUpdateDto.getBio());
        user.setProfileImageUrl(profileUpdateDto.getProfileImageUrl());
        
        userRepository.save(user);
    }
} 
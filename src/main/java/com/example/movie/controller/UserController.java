package com.example.movie.controller;

import com.example.movie.dto.UserProfileDto;
import com.example.movie.dto.UserProfileUpdateDto;
import com.example.movie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        UserProfileDto profile = userService.getProfile(email);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserProfileUpdateDto profileUpdateDto) {
        String email = userDetails.getUsername();
        userService.updateProfile(email, profileUpdateDto);
        return ResponseEntity.ok().build();
    }
} 
package com.example.movie.controller;

import com.example.movie.dto.LoginRequest;
import com.example.movie.dto.MessageResponse;
import com.example.movie.dto.SignupRequest;
import com.example.movie.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest) {
        authService.register(signupRequest);
        return ResponseEntity.ok(new MessageResponse("회원가입이 정상적으로 처리되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Validated @RequestBody LoginRequest loginRequest) {
        String authResponse = authService.login(loginRequest);
        return ResponseEntity.ok(authResponse);
    }
}

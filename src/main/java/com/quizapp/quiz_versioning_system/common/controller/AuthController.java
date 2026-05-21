package com.quizapp.quiz_versioning_system.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.quiz_versioning_system.common.dto.AuthResponse;
import com.quizapp.quiz_versioning_system.common.dto.LoginRequest;
import com.quizapp.quiz_versioning_system.common.dto.request.RegisterRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.RegisterResponse;
import com.quizapp.quiz_versioning_system.common.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request)
            throws Exception {

        return ResponseEntity.ok(
                authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(

            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(

                authService.register(request));
    }

}
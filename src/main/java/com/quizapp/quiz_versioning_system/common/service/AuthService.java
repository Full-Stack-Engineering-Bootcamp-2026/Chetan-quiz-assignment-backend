package com.quizapp.quiz_versioning_system.common.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.common.dto.AuthResponse;
import com.quizapp.quiz_versioning_system.common.dto.LoginRequest;
import com.quizapp.quiz_versioning_system.common.entity.User;
import com.quizapp.quiz_versioning_system.common.repository.UserRepository;
import com.quizapp.quiz_versioning_system.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request)
            throws Exception {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}
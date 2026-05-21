package com.quizapp.quiz_versioning_system.common.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.common.dto.AuthResponse;
import com.quizapp.quiz_versioning_system.common.dto.LoginRequest;
import com.quizapp.quiz_versioning_system.common.dto.request.RegisterRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.RegisterResponse;
import com.quizapp.quiz_versioning_system.common.entity.User;
import com.quizapp.quiz_versioning_system.common.enums.Role;
import com.quizapp.quiz_versioning_system.common.repository.UserRepository;
import com.quizapp.quiz_versioning_system.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

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

        return AuthResponse.builder()

                .token(token)

                .role(user.getRole().name())

                .build();
    }

    
    public RegisterResponse register(
            RegisterRequest request) {

        boolean exists = userRepository
                .findByEmail(request.getEmail())
                .isPresent();

        if (exists) {

            throw new RuntimeException(
                    "Email already exists");
        }

        User user = new User();

        user.setName(
                request.getName());

        user.setEmail(
                request.getEmail());

        user.setPassword(

                passwordEncoder.encode(
                        request.getPassword()));

        user.setRole(
                Role.ROLE_USER);

        userRepository.save(user);

        return RegisterResponse.builder()

                .message(
                        "User registered successfully")

                .build();
    }
}
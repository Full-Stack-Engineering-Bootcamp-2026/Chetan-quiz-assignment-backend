package com.quizapp.quiz_versioning_system.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
@AllArgsConstructor

public class AuthResponse {

    private String token;

    private String role;
}
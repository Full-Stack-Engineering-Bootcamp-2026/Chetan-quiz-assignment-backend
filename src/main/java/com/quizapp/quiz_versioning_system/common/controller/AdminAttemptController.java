package com.quizapp.quiz_versioning_system.common.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.quiz_versioning_system.common.dto.response.AttemptDetailResponse;
import com.quizapp.quiz_versioning_system.common.service.AttemptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/attempts")
@RequiredArgsConstructor
public class AdminAttemptController {

    private final AttemptService attemptService;

    @GetMapping
    public ResponseEntity<List<AttemptDetailResponse>>
    getAllAttempts() {

        return ResponseEntity.ok(
                attemptService.getAllAttempts());
    }
}
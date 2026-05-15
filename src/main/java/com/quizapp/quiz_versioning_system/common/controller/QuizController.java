package com.quizapp.quiz_versioning_system.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuizRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuizResponse;
import com.quizapp.quiz_versioning_system.common.service.QuizService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/quizzes")
@RequiredArgsConstructor
@Validated
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(@Valid @RequestBody CreateQuizRequest request) {

        return new ResponseEntity<>(quizService.createQuiz(request),HttpStatus.CREATED);

    }
    
}
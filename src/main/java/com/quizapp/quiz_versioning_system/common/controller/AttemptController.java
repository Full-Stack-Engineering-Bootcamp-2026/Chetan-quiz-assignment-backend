package com.quizapp.quiz_versioning_system.common.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.quiz_versioning_system.common.dto.request.SubmitQuizRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuizAttemptResponse;
import com.quizapp.quiz_versioning_system.common.service.AttemptService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/quizzes")
@RequiredArgsConstructor
@Validated
public class AttemptController {

    private final AttemptService attemptService;

    @PostMapping("/{quizUuid}/attempt")
    public ResponseEntity<QuizAttemptResponse> submitQuiz(@PathVariable UUID quizUuid,@Valid @RequestBody SubmitQuizRequest request) {

        return new ResponseEntity<>(attemptService.submitQuiz(quizUuid,request),HttpStatus.CREATED);
        
    }
}
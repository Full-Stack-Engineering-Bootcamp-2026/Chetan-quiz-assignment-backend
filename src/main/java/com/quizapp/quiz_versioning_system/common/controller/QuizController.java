package com.quizapp.quiz_versioning_system.common.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

        return new ResponseEntity<>(quizService.createQuiz(request), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {

        return ResponseEntity.ok(
                quizService.getAllQuizzes());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<QuizResponse> getQuizByUuid(@PathVariable UUID uuid) {

        return ResponseEntity.ok(
                quizService.getQuizByUuid(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<QuizResponse> updateQuiz(@PathVariable UUID uuid, @Valid @RequestBody CreateQuizRequest request) {

        return ResponseEntity.ok(quizService.updateQuiz( uuid, request));
        
    }

}
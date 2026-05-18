package com.quizapp.quiz_versioning_system.common.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.quiz_versioning_system.common.dto.response.QuizResponse;
import com.quizapp.quiz_versioning_system.common.service.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/quizzes")
@RequiredArgsConstructor
public class UserQuizController {

    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {

        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<QuizResponse> getQuizByUuid(@PathVariable UUID uuid) {

        return ResponseEntity.ok(quizService.getQuizByUuid(uuid));
    }
}
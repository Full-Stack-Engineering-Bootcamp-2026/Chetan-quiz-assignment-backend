package com.quizapp.quiz_versioning_system.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuestionRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuestionResponse;
import com.quizapp.quiz_versioning_system.common.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/questions")
@RequiredArgsConstructor
@Validated
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@Valid @RequestBody CreateQuestionRequest request) {

        QuestionResponse response = questionService.createQuestion(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
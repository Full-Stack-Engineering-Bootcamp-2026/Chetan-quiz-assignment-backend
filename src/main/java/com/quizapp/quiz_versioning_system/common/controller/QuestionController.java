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

import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuestionRequest;
import com.quizapp.quiz_versioning_system.common.dto.request.UpdateQuestionRequest;
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

    @PutMapping("/{uuid}")
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable UUID uuid,
            @Valid @RequestBody UpdateQuestionRequest request) {

        QuestionResponse response = questionService.updateQuestion(uuid, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {

        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<QuestionResponse> getQuestionByUuid(@PathVariable UUID uuid) {

        return ResponseEntity.ok(questionService.getQuestionByUuid(uuid));

    }

    @GetMapping("/{uuid}/versions")
    public ResponseEntity<List<QuestionResponse>> getAllVersions(@PathVariable UUID uuid) {

        return ResponseEntity.ok(questionService.getAllVersions(uuid));
        
    }
}
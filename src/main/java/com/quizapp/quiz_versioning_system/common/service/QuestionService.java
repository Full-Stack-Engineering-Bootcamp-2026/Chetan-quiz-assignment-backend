package com.quizapp.quiz_versioning_system.common.service;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuestionRequest;
import com.quizapp.quiz_versioning_system.common.dto.request.UpdateQuestionRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuestionResponse;

public interface QuestionService {

    QuestionResponse createQuestion(
            CreateQuestionRequest request);

    QuestionResponse updateQuestion(UUID uuid, UpdateQuestionRequest request);

    List<QuestionResponse> getAllQuestions();

    QuestionResponse getQuestionByUuid(UUID uuid);

    List<QuestionResponse> getAllVersions(UUID uuid);

}
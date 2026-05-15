package com.quizapp.quiz_versioning_system.common.service;

import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuestionRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuestionResponse;

public interface QuestionService {

    QuestionResponse createQuestion(
            CreateQuestionRequest request);
}
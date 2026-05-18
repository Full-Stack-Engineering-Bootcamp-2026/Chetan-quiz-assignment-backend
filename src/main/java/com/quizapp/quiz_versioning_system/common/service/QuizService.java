package com.quizapp.quiz_versioning_system.common.service;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuizRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuizResponse;

public interface QuizService {

    QuizResponse createQuiz(CreateQuizRequest request);

    List<QuizResponse> getAllQuizzes();

    QuizResponse getQuizByUuid( UUID uuid);

}
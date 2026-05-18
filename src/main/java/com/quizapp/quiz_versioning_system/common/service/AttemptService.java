package com.quizapp.quiz_versioning_system.common.service;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.common.dto.request.SubmitQuizRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.AttemptDetailResponse;
import com.quizapp.quiz_versioning_system.common.dto.response.QuizAttemptResponse;

public interface AttemptService {

    QuizAttemptResponse submitQuiz( UUID quizUuid, SubmitQuizRequest request);

    List<AttemptDetailResponse> getMyAttempts();

    List<AttemptDetailResponse> getAllAttempts();

}
package com.quizapp.quiz_versioning_system.common.service;

import java.util.UUID;

import com.quizapp.quiz_versioning_system.common.dto.request.SubmitQuizRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuizAttemptResponse;

public interface AttemptService {

    QuizAttemptResponse submitQuiz( UUID quizUuid, SubmitQuizRequest request);
}
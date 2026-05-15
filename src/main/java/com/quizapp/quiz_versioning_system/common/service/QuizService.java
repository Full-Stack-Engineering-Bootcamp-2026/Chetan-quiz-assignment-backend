package com.quizapp.quiz_versioning_system.common.service;

import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuizRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuizResponse;

public interface QuizService {

    QuizResponse createQuiz(CreateQuizRequest request);
    
}
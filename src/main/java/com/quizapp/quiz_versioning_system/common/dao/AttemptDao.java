package com.quizapp.quiz_versioning_system.common.dao;

import java.util.List;

import com.quizapp.quiz_versioning_system.common.entity.QuizAttempt;

public interface AttemptDao {

    QuizAttempt saveQuizAttempt(
            QuizAttempt quizAttempt);

    List<QuizAttempt> getUserAttempts(String email);

    List<QuizAttempt> getAllAttempts();
}
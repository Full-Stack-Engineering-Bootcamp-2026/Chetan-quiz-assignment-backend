package com.quizapp.quiz_versioning_system.common.dao;

import com.quizapp.quiz_versioning_system.common.entity.QuizAttempt;

public interface AttemptDao {

    QuizAttempt saveQuizAttempt(
            QuizAttempt quizAttempt);
}
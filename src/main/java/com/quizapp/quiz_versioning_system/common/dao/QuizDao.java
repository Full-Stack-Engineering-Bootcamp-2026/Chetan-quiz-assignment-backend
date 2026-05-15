package com.quizapp.quiz_versioning_system.common.dao;

import java.util.UUID;

import com.quizapp.quiz_versioning_system.common.entity.Quiz;

public interface QuizDao {

    Quiz saveQuiz(Quiz quiz);

    Quiz getQuizByUuid(UUID uuid);

    Quiz getQuizWithQuestions(UUID uuid);
}
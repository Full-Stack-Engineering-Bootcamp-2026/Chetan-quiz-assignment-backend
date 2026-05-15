package com.quizapp.quiz_versioning_system.common.dao;

import org.springframework.stereotype.Repository;

import com.quizapp.quiz_versioning_system.common.entity.QuizAttempt;
import com.quizapp.quiz_versioning_system.common.repository.QuizAttemptRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AttemptDaoImpl implements AttemptDao {

    private final QuizAttemptRepository quizAttemptRepository;

    @Override
    public QuizAttempt saveQuizAttempt( QuizAttempt quizAttempt) {

        return quizAttemptRepository .save(quizAttempt);
    }
}
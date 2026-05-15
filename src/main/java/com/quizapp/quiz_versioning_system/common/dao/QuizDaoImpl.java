package com.quizapp.quiz_versioning_system.common.dao;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.quizapp.quiz_versioning_system.common.entity.Quiz;
import com.quizapp.quiz_versioning_system.common.exception.ResourceNotFoundException;
import com.quizapp.quiz_versioning_system.common.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuizDaoImpl
        implements QuizDao {

    private final QuizRepository quizRepository;

    @Override
    public Quiz saveQuiz(Quiz quiz) {

        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuizByUuid(UUID uuid) {

        return quizRepository.findByUuid(uuid)
                        .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
    }

    @Override
    public Quiz getQuizWithQuestions(UUID uuid) {

        return quizRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
    }
}
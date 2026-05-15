package com.quizapp.quiz_versioning_system.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.common.entity.QuizAttempt;

public interface QuizAttemptRepository
        extends JpaRepository<QuizAttempt, Long> {

}
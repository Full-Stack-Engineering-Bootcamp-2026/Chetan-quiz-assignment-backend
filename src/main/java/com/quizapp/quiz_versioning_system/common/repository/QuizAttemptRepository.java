package com.quizapp.quiz_versioning_system.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.common.entity.QuizAttempt;

public interface QuizAttemptRepository
        extends JpaRepository<QuizAttempt, Long> {

            List<QuizAttempt> findByUserEmailOrderBySubmittedAtDesc(String email);

            List<QuizAttempt> findAllByOrderBySubmittedAtDesc();
}
package com.quizapp.quiz_versioning_system.common.repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.common.entity.Quiz;
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Optional<Quiz> findByUuid(UUID uuid);

    List<Quiz> findByActiveTrue();
}
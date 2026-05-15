package com.quizapp.quiz_versioning_system.common.repository;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.common.entity.QuestionMaster;

public interface QuestionMasterRepository
        extends JpaRepository<QuestionMaster, Long> {

    Optional<QuestionMaster> findByUuid(UUID uuid);
}
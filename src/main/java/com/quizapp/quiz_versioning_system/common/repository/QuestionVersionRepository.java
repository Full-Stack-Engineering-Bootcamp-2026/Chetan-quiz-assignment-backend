package com.quizapp.quiz_versioning_system.common.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.common.entity.QuestionVersion;

public interface QuestionVersionRepository
        extends JpaRepository<QuestionVersion, Long> {

    Optional<QuestionVersion> findByQuestionMaster_UuidAndLatestTrue(UUID uuid);

    List<QuestionVersion> findByQuestionMasterUuidOrderByVersionNumberDesc(UUID uuid);

}
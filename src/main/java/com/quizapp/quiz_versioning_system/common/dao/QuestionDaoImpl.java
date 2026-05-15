package com.quizapp.quiz_versioning_system.common.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.quizapp.quiz_versioning_system.common.entity.QuestionMaster;
import com.quizapp.quiz_versioning_system.common.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.common.exception.ResourceNotFoundException;
import com.quizapp.quiz_versioning_system.common.repository.QuestionMasterRepository;
import com.quizapp.quiz_versioning_system.common.repository.QuestionVersionRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuestionDaoImpl
        implements QuestionDao {

    private final QuestionMasterRepository questionMasterRepository;

    private final QuestionVersionRepository questionVersionRepository;

    @Override
    public QuestionMaster saveQuestionMaster(QuestionMaster questionMaster) {

        return questionMasterRepository.save(questionMaster);
    }

    @Override
    public QuestionVersion saveQuestionVersion(QuestionVersion questionVersion) {

        return questionVersionRepository.save(questionVersion);
    }

    @Override
    public QuestionMaster getQuestionMasterByUuid(UUID uuid) {

        return questionMasterRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Question master not found"));
    }

    @Override
    public QuestionVersion getLatestVersion(UUID uuid) {

        return questionVersionRepository.findByQuestionMaster_UuidAndLatestTrue(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Latest question version not found"));
    }

    @Override
    public List<QuestionVersion> getAllVersions(UUID uuid) {

        return questionVersionRepository
                .findByQuestionMasterUuidOrderByVersionNumberDesc(uuid);
    }
}
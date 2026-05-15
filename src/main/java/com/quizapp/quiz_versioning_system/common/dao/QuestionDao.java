package com.quizapp.quiz_versioning_system.common.dao;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.common.entity.QuestionMaster;
import com.quizapp.quiz_versioning_system.common.entity.QuestionVersion;

public interface QuestionDao {

    QuestionMaster saveQuestionMaster(
            QuestionMaster questionMaster);

    QuestionVersion saveQuestionVersion(
            QuestionVersion questionVersion);

    QuestionMaster getQuestionMasterByUuid(
            UUID uuid);

    QuestionVersion getLatestVersion(
            UUID uuid);

    List<QuestionVersion> getAllVersions(
            UUID uuid);

    List<QuestionVersion> getAllLatestQuestions();

    QuestionVersion getLatestQuestionByUuid(
        UUID uuid);
}
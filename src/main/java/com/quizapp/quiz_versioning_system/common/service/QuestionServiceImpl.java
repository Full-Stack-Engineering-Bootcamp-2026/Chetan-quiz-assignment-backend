package com.quizapp.quiz_versioning_system.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.common.dao.QuestionDao;
import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuestionRequest;
import com.quizapp.quiz_versioning_system.common.dto.request.UpdateQuestionRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuestionResponse;
import com.quizapp.quiz_versioning_system.common.entity.QuestionMaster;
import com.quizapp.quiz_versioning_system.common.entity.QuestionOption;
import com.quizapp.quiz_versioning_system.common.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.common.enums.AnswerType;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    @Override
    public QuestionResponse createQuestion(CreateQuestionRequest request) {

        QuestionMaster questionMaster = new QuestionMaster();

        questionMaster.setActive(true);

        questionMaster = questionDao.saveQuestionMaster(questionMaster);

        QuestionVersion questionVersion = new QuestionVersion();

        questionVersion.setQuestionMaster(questionMaster);

        questionVersion.setVersionNumber(1);

        questionVersion.setLatest(true);

        questionVersion.setQuestionText(request.getQuestionText());

        questionVersion.setAnswerType(request.getAnswerType());

        List<QuestionOption> options = new ArrayList<>();

        if (request.getAnswerType() != AnswerType.TEXTAREA && request.getOptions() != null) {

            for (String optionText : request.getOptions()) {

                QuestionOption option = new QuestionOption();

                option.setOptionText(optionText);

                option.setQuestionVersion(questionVersion);

                options.add(option);
            }
        }

        questionVersion.setOptions(options);

        questionVersion = questionDao.saveQuestionVersion(questionVersion);

        return QuestionResponse.builder()

                .questionUuid(questionMaster.getUuid())
                .versionNumber(questionVersion.getVersionNumber())
                .questionText(questionVersion.getQuestionText())
                .answerType(questionVersion.getAnswerType())
                .options(options.stream()
                        .map(QuestionOption::getOptionText)
                        .toList())
                .build();

    }

    @Override
    public QuestionResponse updateQuestion(UUID uuid, UpdateQuestionRequest request) {

        QuestionVersion latestVersion = questionDao.getLatestVersion(uuid);
        latestVersion.setLatest(false);
        questionDao.saveQuestionVersion(latestVersion);

        QuestionVersion newVersion = new QuestionVersion();
        newVersion.setQuestionMaster(latestVersion.getQuestionMaster());
        newVersion.setVersionNumber(latestVersion.getVersionNumber() + 1);
        newVersion.setLatest(true);
        newVersion.setQuestionText(request.getQuestionText());
        newVersion.setAnswerType(request.getAnswerType());

        List<QuestionOption> options = new ArrayList<>();

        if (request.getAnswerType() != AnswerType.TEXTAREA && request.getOptions() != null) {

            for (String optionText : request.getOptions()) {
                QuestionOption option = new QuestionOption();
                option.setOptionText(optionText);
                option.setQuestionVersion(newVersion);
                options.add(option);
            }
        }
        newVersion.setOptions(options);

        newVersion = questionDao.saveQuestionVersion(newVersion);

        return QuestionResponse.builder()
                .questionUuid(latestVersion.getQuestionMaster().getUuid())

                .versionNumber(newVersion.getVersionNumber())

                .questionText(newVersion.getQuestionText())

                .answerType(newVersion.getAnswerType())

                .options(options.stream()
                        .map(QuestionOption::getOptionText)
                        .toList())
                .build();
    }

    @Override
    public List<QuestionResponse> getAllQuestions() {

        List<QuestionVersion> questions = questionDao.getAllLatestQuestions();

        return questions.stream().map(this::mapToResponse).toList();
    }

    @Override
    public QuestionResponse getQuestionByUuid(UUID uuid) {

        QuestionVersion questionVersion = questionDao.getLatestQuestionByUuid(uuid);

        return mapToResponse(questionVersion);
    }

    @Override
    public List<QuestionResponse> getAllVersions(UUID uuid) {

        List<QuestionVersion> versions = questionDao.getAllVersions(uuid);

        return versions.stream().map(this::mapToResponse).toList();
    }

    private QuestionResponse mapToResponse(
            QuestionVersion questionVersion) {

        return QuestionResponse.builder()
                .questionUuid(questionVersion.getQuestionMaster().getUuid())
                .versionNumber(questionVersion.getVersionNumber())
                .questionText(questionVersion.getQuestionText())
                .answerType(questionVersion.getAnswerType())
                .options(questionVersion.getOptions().stream()
                        .map(QuestionOption::getOptionText).toList())
                .build();
    }

}
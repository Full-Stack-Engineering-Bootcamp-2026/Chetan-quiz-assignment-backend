package com.quizapp.quiz_versioning_system.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.common.dao.QuestionDao;
import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuestionRequest;
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

         List<QuestionOption> options =new ArrayList<>();

        if (request.getAnswerType() !=AnswerType.TEXTAREA && request.getOptions() != null) {

            for (String optionText :request.getOptions()) {

                QuestionOption option =new QuestionOption();

                option.setOptionText(optionText);

                option.setQuestionVersion( questionVersion);

                options.add(option);
            }
        }

        questionVersion.setOptions(options);

        questionVersion =questionDao.saveQuestionVersion(questionVersion);

        return QuestionResponse.builder()

                .questionUuid( questionMaster.getUuid())
                .versionNumber(questionVersion.getVersionNumber())
                .questionText( questionVersion.getQuestionText())
                .answerType(questionVersion.getAnswerType())
                .options(options.stream()
                            .map(QuestionOption::getOptionText)
                            .toList())
                .build();



        
    }

}
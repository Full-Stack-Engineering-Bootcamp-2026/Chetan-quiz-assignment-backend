package com.quizapp.quiz_versioning_system.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quizapp.quiz_versioning_system.common.dao.QuestionDao;
import com.quizapp.quiz_versioning_system.common.dao.QuizDao;
import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuizRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuestionResponse;
import com.quizapp.quiz_versioning_system.common.dto.response.QuizResponse;
import com.quizapp.quiz_versioning_system.common.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.common.entity.Quiz;
import com.quizapp.quiz_versioning_system.common.entity.QuizQuestion;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizDao quizDao;

    private final QuestionDao questionDao;

    @Override
    public QuizResponse createQuiz(CreateQuizRequest request) {
        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        quiz.setActive(true);

        List<QuizQuestion> quizQuestions = new ArrayList<>();
        List<QuestionResponse> responses = new ArrayList<>();

        for (UUID questionUuid : request.getQuestionUuids()) {

            QuestionVersion questionVersion = questionDao.getLatestVersion(questionUuid);
            QuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.setQuiz(quiz);
            quizQuestion.setQuestionVersion(questionVersion);
            quizQuestions.add(quizQuestion);

            QuestionResponse response = QuestionResponse.builder()
                    .questionUuid(questionUuid)
                    .versionNumber(questionVersion.getVersionNumber())
                    .questionText(questionVersion.getQuestionText())
                    .answerType(questionVersion.getAnswerType())
                    .options(questionVersion.getOptions()
                            .stream().map(option -> option.getOptionText()).toList())
                    .build();

            responses.add(response);
        }
        quiz.setQuizQuestions(quizQuestions);

        quiz = quizDao.saveQuiz(quiz);

        return QuizResponse.builder()
                .quizUuid(quiz.getUuid())
                .title(quiz.getTitle())
                .questions(responses)
                .build();

    }

    @Override
    public List<QuizResponse> getAllQuizzes() {

        List<Quiz> quizzes = quizDao.getAllActiveQuizzes();

        return quizzes.stream().map(quiz -> QuizResponse.builder().quizUuid(quiz.getUuid())
                .title(quiz.getTitle()).build()).toList();
    }

    @Override
    public QuizResponse getQuizByUuid(UUID uuid) {
        Quiz quiz = quizDao.getQuizByUuid(uuid);

        List<QuestionResponse> questions = quiz.getQuizQuestions().stream()
                .map(quizQuestion -> {
                    QuestionVersion qv = quizQuestion.getQuestionVersion();

                    return QuestionResponse.builder()
                            .questionUuid(qv.getQuestionMaster().getUuid())
                            .versionNumber(qv.getVersionNumber())
                            .questionText(qv.getQuestionText())
                            .answerType(qv.getAnswerType())
                            .options(qv.getOptions().stream()
                                    .map(option -> option.getOptionText())
                                    .toList())
                            .build();
                })
                .toList();

        return QuizResponse.builder()
                .quizUuid(quiz.getUuid())
                .title(quiz.getTitle())
                .questions(questions)
                .build();
    }

}
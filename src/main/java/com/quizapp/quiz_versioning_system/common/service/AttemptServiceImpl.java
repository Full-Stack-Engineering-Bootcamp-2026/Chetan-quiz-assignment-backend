package com.quizapp.quiz_versioning_system.common.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quizapp.quiz_versioning_system.common.dao.AttemptDao;
import com.quizapp.quiz_versioning_system.common.dao.QuestionDao;
import com.quizapp.quiz_versioning_system.common.dao.QuizDao;
import com.quizapp.quiz_versioning_system.common.dto.request.SubmitAnswerRequest;
import com.quizapp.quiz_versioning_system.common.dto.request.SubmitQuizRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuizAttemptResponse;
import com.quizapp.quiz_versioning_system.common.entity.AttemptAnswer;
import com.quizapp.quiz_versioning_system.common.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.common.entity.Quiz;
import com.quizapp.quiz_versioning_system.common.entity.QuizAttempt;
import com.quizapp.quiz_versioning_system.common.entity.User;
import com.quizapp.quiz_versioning_system.common.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class AttemptServiceImpl implements AttemptService {

    private final AttemptDao attemptDao;

    private final QuizDao quizDao;

    private final QuestionDao questionDao;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Override
    public QuizAttemptResponse submitQuiz(UUID quizUuid, SubmitQuizRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        Quiz quiz = quizDao.getQuizWithQuestions(quizUuid);

        QuizAttempt quizAttempt = new QuizAttempt();
        quizAttempt.setQuiz(quiz);
        quizAttempt.setUser(user);
        quizAttempt.setSubmittedAt(LocalDateTime.now());

        List<AttemptAnswer> attemptAnswers = new ArrayList<>();

        for (SubmitAnswerRequest answerRequest : request.getAnswers()) {

            QuestionVersion questionVersion = quiz.getQuizQuestions().stream().filter(quizQuestion ->
                    quizQuestion.getQuestionVersion().getQuestionMaster().getUuid()
                    .equals(answerRequest.getQuestionUuid()))
                    .map(quizQuestion -> quizQuestion.getQuestionVersion())
                    .findFirst().orElseThrow(() -> new RuntimeException("Question not found in quiz"));

            AttemptAnswer attemptAnswer = new AttemptAnswer();
            attemptAnswer.setQuizAttempt(quizAttempt);
            attemptAnswer.setQuestionVersion(questionVersion);
            String snapshot = questionVersion.getQuestionText();
            attemptAnswer.setQuestionSnapshot(snapshot);

            try {

                String submittedAnswer = objectMapper.writeValueAsString(answerRequest.getAnswers());
                attemptAnswer.setSubmittedAnswer(submittedAnswer);

            } catch (Exception e) {
                throw new RuntimeException(
                        "Failed to serialize answers");
            }
            attemptAnswers.add(attemptAnswer);
        }
        quizAttempt.setAnswers(attemptAnswers);

        quizAttempt = attemptDao.saveQuizAttempt(quizAttempt);
        return QuizAttemptResponse.builder().attemptUuid(quizAttempt.getUuid())
                .quizUuid(quiz.getUuid())
                .submittedAt(quizAttempt.getSubmittedAt())
                .message("Quiz submitted successfully")
                .build();
    }
}
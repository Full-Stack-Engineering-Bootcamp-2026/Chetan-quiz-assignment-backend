package com.quizapp.quiz_versioning_system.common.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.quizapp.quiz_versioning_system.common.dto.request.CreateQuizRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.QuizResponse;
import com.quizapp.quiz_versioning_system.common.service.QuizService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QuizGraphQLController {

    private final QuizService quizService;

    @QueryMapping
    public List<QuizResponse> quizzes() {

        return quizService.getAllQuizzes();
    }

    @QueryMapping
    public QuizResponse quizByUuid(@Argument String uuid) {

        return quizService.getQuizByUuid(UUID.fromString(uuid));

    }

    @MutationMapping
    public QuizResponse createQuiz(
            @Argument String title,
            @Argument List<String> questionUuids) {

        CreateQuizRequest request = new CreateQuizRequest();
        request.setTitle(title);
        request.setQuestionUuids(
                questionUuids.stream().map(UUID::fromString)
                        .toList());

        return quizService.createQuiz(request);
    }

}
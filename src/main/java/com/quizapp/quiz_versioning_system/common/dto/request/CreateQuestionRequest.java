package com.quizapp.quiz_versioning_system.common.dto.request;


import java.util.List;

import com.quizapp.quiz_versioning_system.common.enums.AnswerType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionRequest {

    @NotBlank(message = "Question text is required")
    private String questionText;

    @NotNull(message = "Answer type is required")
    private AnswerType answerType;

    private List<String> options;
}
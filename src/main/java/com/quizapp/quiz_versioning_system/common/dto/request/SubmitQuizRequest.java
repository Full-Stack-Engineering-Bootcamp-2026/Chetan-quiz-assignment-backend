package com.quizapp.quiz_versioning_system.common.dto.request;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitQuizRequest {

    @NotEmpty(message = "Answers are required")
    private List<SubmitAnswerRequest> answers;
}
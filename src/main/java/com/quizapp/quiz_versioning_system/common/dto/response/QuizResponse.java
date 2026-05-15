package com.quizapp.quiz_versioning_system.common.dto.response;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuizResponse {

    private UUID quizUuid;

    private String title;

    private List<QuestionResponse> questions;
}
package com.quizapp.quiz_versioning_system.common.dto.response;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class QuizResponse {

    private UUID quizUuid;

    private String title;

    private List<QuestionResponse> questions;

    private Integer questionCount;
}
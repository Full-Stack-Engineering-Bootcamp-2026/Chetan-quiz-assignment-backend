package com.quizapp.quiz_versioning_system.common.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class QuizAttemptResponse {

    private UUID attemptUuid;

    private UUID quizUuid;

    private LocalDateTime submittedAt;

    private String message;
}
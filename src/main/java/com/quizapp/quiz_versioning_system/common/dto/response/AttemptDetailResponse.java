package com.quizapp.quiz_versioning_system.common.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AttemptDetailResponse {

    private UUID attemptUuid;

    private UUID quizUuid;

    private String quizTitle;

    private LocalDateTime submittedAt;

    private String userName;

    private String userEmail;

    private List<AttemptAnswerResponse> answers;
}
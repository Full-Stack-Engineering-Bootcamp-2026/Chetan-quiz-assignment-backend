package com.quizapp.quiz_versioning_system.common.dto.response;


import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.common.enums.AnswerType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionResponse {

    private UUID questionUuid;

    private Integer versionNumber;

    private String questionText;

    private AnswerType answerType;

    private List<String> options;
}
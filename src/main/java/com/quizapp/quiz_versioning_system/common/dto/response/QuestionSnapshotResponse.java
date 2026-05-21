package com.quizapp.quiz_versioning_system.common.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionSnapshotResponse {

    private String questionText;

    private String answerType;

    private Integer versionNumber;

    private List<String> options;
}
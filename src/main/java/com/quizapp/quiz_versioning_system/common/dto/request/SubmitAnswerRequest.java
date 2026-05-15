package com.quizapp.quiz_versioning_system.common.dto.request;


import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitAnswerRequest {

    private UUID questionUuid;

    private List<String> answers;  
}
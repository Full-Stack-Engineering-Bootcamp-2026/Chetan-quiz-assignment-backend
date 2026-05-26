package com.quizapp.quiz_versioning_system.common.dto.request;


import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuizGraphQLRequest {

    private String title;

    private List<String> questionUuids;
}
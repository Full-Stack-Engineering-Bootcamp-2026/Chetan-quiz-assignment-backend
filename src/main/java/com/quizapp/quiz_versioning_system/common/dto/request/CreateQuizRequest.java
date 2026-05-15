package com.quizapp.quiz_versioning_system.common.dto.request;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuizRequest {

     @NotBlank(message = "Quiz title is required")
    private String title;

    @NotEmpty(message = "At least one question is required")
    private List<UUID> questionUuids;
    
}
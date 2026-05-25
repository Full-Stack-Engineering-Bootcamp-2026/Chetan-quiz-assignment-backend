package com.quizapp.quiz_versioning_system.common.dto.request;


import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfilePictureRequest {

    @NotBlank
    private String fileKey;
}
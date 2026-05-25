package com.quizapp.quiz_versioning_system.common.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PresignedUrlResponse {

    private String uploadUrl;

    private String fileKey;
}
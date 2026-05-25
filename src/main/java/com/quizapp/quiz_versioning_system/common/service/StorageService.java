package com.quizapp.quiz_versioning_system.common.service;

import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.common.dto.response.PresignedUrlResponse;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final S3Presigner s3Presigner;

    @Value("${cloud.s3.bucket}")
    private String bucket;

    public PresignedUrlResponse generatePresignedUrl(String fileName) {

        String fileKey = "profiles/"
                + UUID.randomUUID()
                + "-"
                + fileName;

        PutObjectRequest putObjectRequest =

                PutObjectRequest.builder().bucket(bucket).key(fileKey)
                        .contentType("image/jpeg")
                        .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(5))
                .putObjectRequest(putObjectRequest)
                .build();

        String uploadUrl = s3Presigner.presignPutObject(presignRequest)
                .url()
                .toString();

        String publicUrl = "https://f005.backblazeb2.com/file/"
                + bucket
                + "/"
                + fileKey;

        return PresignedUrlResponse.builder()

                .uploadUrl(uploadUrl)

                .fileKey(fileKey)

                .build();
    }

    public String generateViewUrl(
        String fileKey
) {

    GetObjectRequest getObjectRequest =

            GetObjectRequest.builder()

                    .bucket(bucket)

                    .key(fileKey)

                    .build();

    GetObjectPresignRequest presignRequest =

            GetObjectPresignRequest.builder()

                    .signatureDuration(
                            Duration.ofMinutes(5)
                    )

                    .getObjectRequest(
                            getObjectRequest
                    )

                    .build();

    return s3Presigner

            .presignGetObject(
                    presignRequest
            )

            .url()

            .toString();
}

}
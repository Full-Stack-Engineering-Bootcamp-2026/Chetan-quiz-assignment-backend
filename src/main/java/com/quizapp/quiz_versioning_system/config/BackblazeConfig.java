package com.quizapp.quiz_versioning_system.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class BackblazeConfig {

    @Value("${cloud.s3.access-key}")
    private String accessKey;

    @Value("${cloud.s3.secret-key}")
    private String secretKey;

    @Value("${cloud.s3.region}")
    private String region;

    @Value("${cloud.s3.endpoint}")
    private String endpoint;

    @Bean
    public S3Client s3Client() {

        AwsBasicCredentials credentials =

                AwsBasicCredentials.create(accessKey, secretKey);

        return S3Client.builder().endpointOverride(URI.create(endpoint)).region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        return S3Presigner.builder().endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}
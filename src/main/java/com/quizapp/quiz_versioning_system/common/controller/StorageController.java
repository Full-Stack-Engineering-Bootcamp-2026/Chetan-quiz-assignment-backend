package com.quizapp.quiz_versioning_system.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.quiz_versioning_system.common.dto.response.PresignedUrlResponse;
import com.quizapp.quiz_versioning_system.common.service.StorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping("/presigned-url")
    public ResponseEntity<PresignedUrlResponse> generatePresignedUrl(@RequestParam String fileName) {

        return ResponseEntity.ok(storageService.generatePresignedUrl(fileName));
    }

    @GetMapping("/view-url")
    public ResponseEntity<String> generateViewUrl(

            @RequestParam String fileKey) {

        return ResponseEntity.ok(

                storageService.generateViewUrl(
                        fileKey));
    }

}
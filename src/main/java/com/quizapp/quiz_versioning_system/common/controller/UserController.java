package com.quizapp.quiz_versioning_system.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.quizapp.quiz_versioning_system.common.dto.request.UpdateProfilePictureRequest;
import com.quizapp.quiz_versioning_system.common.dto.response.UserProfileResponse;
import com.quizapp.quiz_versioning_system.common.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/profile-picture")
    public ResponseEntity<String> updateProfilePicture(
            Authentication authentication,
            @Valid @RequestBody UpdateProfilePictureRequest request) {

        userService.updateProfilePicture(
                authentication.getName(),
                request.getFileKey());
        return ResponseEntity.ok(
                "Profile picture updated");
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(
            Authentication authentication) {
        return ResponseEntity.ok(
                userService.getProfile(
                        authentication.getName()));
    }
}
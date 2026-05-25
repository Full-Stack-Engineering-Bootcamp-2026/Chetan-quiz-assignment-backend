package com.quizapp.quiz_versioning_system.common.service;

import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.common.dto.response.UserProfileResponse;
import com.quizapp.quiz_versioning_system.common.entity.User;
import com.quizapp.quiz_versioning_system.common.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final StorageService storageService;

    private final UserRepository userRepository;

    public void updateProfilePicture(

            String email,

            String fileKey) {

        User user = userRepository

                .findByEmail(email)

                .orElseThrow(() ->

                new RuntimeException(
                        "User not found"));

        user.setProfilePictureKey(
                fileKey);

        userRepository.save(user);
    }

    public UserProfileResponse getProfile(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("User not found"));

        String imageUrl = null;

        if (user.getProfilePictureKey() != null) {
            imageUrl = storageService.generateViewUrl(user.getProfilePictureKey());
        }

        return UserProfileResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .profileImageUrl(imageUrl)
                .build();
    }

}
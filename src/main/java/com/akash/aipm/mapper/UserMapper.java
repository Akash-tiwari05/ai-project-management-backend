package com.akash.aipm.mapper;

import com.akash.aipm.dto.response.UserResponse;
import com.akash.aipm.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        if (user == null) return null;
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .initials(user.getInitials())        // Pure pass-through
                .avatarColor(user.getAvatarColor())  // Pure pass-through
                .role(user.getRole())
                .skills(user.getSkills())
                .status(user.getStatus())
                .planType(user.getPlanId())
                .emailVerified(user.isEmailVerified())
                .lastLoginAt(user.getLastLoginAt())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
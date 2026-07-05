package com.akash.aipm.dto.response;

import com.akash.aipm.enums.MemberRole;
import com.akash.aipm.enums.PlanType;
import com.akash.aipm.enums.UserStatus;
import lombok.Builder;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record UserResponse(

    UUID id,
    String name,
    String email,
    String initials,
    String avatarColor,
    MemberRole role,
    List<String> skills,
    UserStatus status,
    PlanType planType,
    boolean emailVerified,
    Instant lastLoginAt,
    Instant createdAt
){
}
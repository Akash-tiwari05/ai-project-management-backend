package com.akash.aipm.dto.response;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;

@Builder
public record ProjectMemberResponse(
        UUID id,
        UserResponse user,
        String role,
        Instant joinedAt
) {
}

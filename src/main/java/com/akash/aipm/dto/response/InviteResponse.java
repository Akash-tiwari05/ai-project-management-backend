package com.akash.aipm.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record InviteResponse(
        UUID id,
        String email,
        String role,
        String status,
        String message,
        UserResponse invitedBy,
        UUID projectId,
        String projectName,
        Instant expiresAt,
        Instant respondedAt,
        Instant sentAt,
        boolean expired,
        boolean pending
) {
}
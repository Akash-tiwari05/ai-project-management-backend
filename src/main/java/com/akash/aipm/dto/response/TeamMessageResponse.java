package com.akash.aipm.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record TeamMessageResponse(
        UUID id,
        String text,
        String type,
        Object metadata,
        boolean edited,
        Instant editedAt,
        boolean deleted,
        TeamMessageResponse replyTo,
        UserResponse sender,
        UUID projectId,
        long readCount,
        boolean readByCurrentUser,
        Instant sentAt,
        Instant createdAt
) {
}
package com.akash.aipm.dto.response;

import com.akash.aipm.enums.AiRole;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record AiMessageResponse(
        UUID id,
        String text,
        AiRole role,
        String model,
        Integer promptTokens,
        Integer completionTokens,
        Integer totalTokens,
        Integer latencyMs,
        UserResponse sender,
        UUID projectId,
        Instant sentAt,
        Instant createdAt
) {
}
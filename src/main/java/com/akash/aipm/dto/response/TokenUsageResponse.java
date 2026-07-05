package com.akash.aipm.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record TokenUsageResponse(
        UUID userId,
        LocalDate date,
        int tokensUsed,
        int requestsMade,
        int planLimit,
        int remaining,
        double usagePercentage
) {
}
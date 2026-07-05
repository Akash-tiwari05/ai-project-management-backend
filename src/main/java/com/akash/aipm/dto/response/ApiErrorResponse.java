package com.akash.aipm.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ApiErrorResponse(
        int status,
        String error,
        String message,
        String path,
        Instant timestamp
) {
}
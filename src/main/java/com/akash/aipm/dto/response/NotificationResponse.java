package com.akash.aipm.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record NotificationResponse(
        UUID id,
        String type,
        String title,
        String body,
        String icon,
        String color,
        String linkType,
        UUID linkId,
        boolean read,
        Instant readAt,
        boolean dismissed,
        Instant createdAt
) {
}
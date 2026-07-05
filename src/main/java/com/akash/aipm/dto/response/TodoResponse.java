package com.akash.aipm.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record TodoResponse(
        UUID id,
        String text,
        boolean done,
        String priority,
        LocalDate dueDate,
        int position,
        Instant completedAt,
        UserResponse assignee,
        UserResponse createdBy,
        UUID projectId,
        boolean overdue,
        Instant createdAt,
        Instant updatedAt
) {
}

package com.akash.aipm.dto.request;

import com.akash.aipm.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record SendMessageRequest(

        @NotBlank(message = "Text is required")
        @Size(max = 1000, message = "Text cannot exceed 1000 characters")
        String text,

        Boolean done,

        @NotNull(message = "Priority is required")
        Priority priority,

        LocalDate dueDate,

        UUID assigneeId,

        Integer position
) {
}

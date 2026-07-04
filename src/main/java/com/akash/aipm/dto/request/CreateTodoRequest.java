package com.akash.aipm.dto.request;

import com.akash.aipm.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record CreateTodoRequest(

        @NotBlank(message = "Todo text is required")
        @Size(min = 1, max = 1000, message = "Text must be between 1 and 1000 characters")
        String text,

        @NotNull(message = "Priority is required")
        Priority priority,

        LocalDate dueDate,

        UUID assigneeId,

        Integer position
)
{}

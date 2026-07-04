package com.akash.aipm.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AiMessageRequest(

        @NotBlank(message = "Message text is required")
        @Size(max = 5000, message = "Message too long")
        String text
) {}

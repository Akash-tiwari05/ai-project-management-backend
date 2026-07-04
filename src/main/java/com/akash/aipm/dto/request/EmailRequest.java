package com.akash.aipm.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequest(

        @NotBlank(message = "To address is required")
        @Email(message = "Invalid email format")
        String to,

        @NotBlank(message = "Subject is required")
        String subject,

        @NotBlank(message = "Body is required")
        String body,

        boolean isHtml
) {
}

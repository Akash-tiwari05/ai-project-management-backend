package com.akash.aipm.dto.request;

import com.akash.aipm.enums.MemberRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateInviteRequest(

        @NotNull(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Role is required")
        MemberRole role,

        String message
) {
}

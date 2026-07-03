package com.akash.aipm.dto.request;

import com.akash.aipm.enums.MemberRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

public record AddMemberRequest (

        @NotNull(message = "User ID is required")
        UUID userId,

        @NotNull(message = "Role is required")
        MemberRole memberRole
){}
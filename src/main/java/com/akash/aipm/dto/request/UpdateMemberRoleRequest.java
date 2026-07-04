package com.akash.aipm.dto.request;

import com.akash.aipm.enums.MemberRole;
import jakarta.validation.constraints.NotNull;


public record UpdateMemberRoleRequest(

        @NotNull(message = "Role is required")
        MemberRole memberRole
)
{}

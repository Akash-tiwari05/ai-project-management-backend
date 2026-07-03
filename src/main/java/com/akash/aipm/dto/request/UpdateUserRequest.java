package com.akash.aipm.dto.request;

import jakarta.validation.constraints.Size;


import java.util.List;


public record UpdateUserRequest (
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    String name,

    String avatarColor,

    List<String> skills,

    String status
){}
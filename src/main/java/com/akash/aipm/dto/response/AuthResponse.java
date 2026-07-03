package com.akash.aipm.dto.response;

import lombok.Builder;


@Builder
public record AuthResponse (

    String accessToken,
    String tokenType,
    long expiresIn,
    UserResponse user
){}
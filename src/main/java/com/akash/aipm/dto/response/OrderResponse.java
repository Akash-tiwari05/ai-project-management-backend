package com.akash.aipm.dto.response;

import lombok.Builder;

@Builder
public record OrderResponse(
        String orderId,
        String keyId,
        int amount,
        String currency,
        String planId,
        String billingCycle
) {
}
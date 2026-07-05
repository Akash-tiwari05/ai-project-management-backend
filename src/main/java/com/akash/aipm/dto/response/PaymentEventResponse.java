package com.akash.aipm.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record PaymentEventResponse(
        UUID id,
        String eventType,
        String razorpayPaymentId,
        Integer amount,
        String currency,
        String status,
        Instant processedAt
) {
}
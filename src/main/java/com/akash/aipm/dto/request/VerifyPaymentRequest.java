package com.akash.aipm.dto.request;

import jakarta.validation.constraints.NotBlank;

public record VerifyPaymentRequest(

        @NotBlank(message = "Order ID is required")
        String razorpayOrderId,

        @NotBlank(message = "Payment ID is required")
        String razorpayPaymentId,

        @NotBlank(message = "Signature is required")
        String razorpaySignature
) {
}

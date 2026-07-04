package com.akash.aipm.dto.request;

import com.akash.aipm.enums.BillingCycle;
import com.akash.aipm.enums.PlanType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(

        @NotNull(message = "Plan Type is required")
        PlanType planType,

        @NotNull(message = "Billing cycle is required")
        BillingCycle billingCycle
) {
}

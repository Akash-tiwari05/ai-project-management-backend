package com.akash.aipm.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record SubscriptionResponse(
        UUID id,
        String planId,
        String status,
        String billingCycle,
        int monthlyPriceUsd,
        int maxProjects,
        int aiTokenLimit,
        int maxMembersPerProject,
        String razorpaySubscriptionId,
        Instant currentPeriodStart,
        Instant currentPeriodEnd,
        boolean cancelAtPeriodEnd,
        Instant cancelledAt,
        Instant trialEndsAt,
        boolean active,
        boolean inTrial,
        boolean expired,
        Instant createdAt,
        Instant updatedAt
) {
}
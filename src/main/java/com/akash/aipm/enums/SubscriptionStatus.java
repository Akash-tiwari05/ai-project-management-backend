package com.akash.aipm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SubscriptionStatus {
    ACTIVE("active"),
    CANCELLED("cancelled"),
    PAST_DUE("past_due"),
    EXPIRED("expired");

    private final String value;

    SubscriptionStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SubscriptionStatus from(String value) {
        for (SubscriptionStatus s : values()) {
            if (s.value.equalsIgnoreCase(value)) return s;
        }
        throw new IllegalArgumentException("Unknown subscription status: " + value);
    }
}
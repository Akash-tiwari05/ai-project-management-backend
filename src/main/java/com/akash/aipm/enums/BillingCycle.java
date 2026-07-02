package com.akash.aipm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BillingCycle {
    MONTHLY("monthly"),
    ANNUAL("annual");

    private final String value;

    BillingCycle(String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static BillingCycle from(String value) {
        for (BillingCycle b : values()) {
            if (b.value.equalsIgnoreCase(value)) return b;
        }
        throw new IllegalArgumentException("Unknown billing cycle: " + value);
    }
}
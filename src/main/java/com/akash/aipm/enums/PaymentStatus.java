package com.akash.aipm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus {
    CAPTURED("captured"),
    FAILED("failed"),
    REFUNDED("refunded"),
    PENDING("pending");

    private final String value;

    PaymentStatus(String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static PaymentStatus from(String value) {
        for (PaymentStatus s : values()) {
            if (s.value.equalsIgnoreCase(value)) return s;
        }
        throw new IllegalArgumentException("Unknown payment status: " + value);
    }
}
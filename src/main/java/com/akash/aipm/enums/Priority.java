package com.akash.aipm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Priority {
    LOW("low", 1),
    MEDIUM("medium", 2),
    HIGH("high", 3);

    private final String value;
    private final int order;

    Priority(String value, int order) {
        this.value = value;
        this.order = order;
    }

    @JsonValue
    public String getValue() { return value; }

    public int getOrder() { return order; }

    public boolean isHigherThan(Priority other) {
        return this.order > other.order;
    }

    @JsonCreator
    public static Priority from(String value) {
        for (Priority p : values()) {
            if (p.value.equalsIgnoreCase(value)) return p;
        }
        throw new IllegalArgumentException("Unknown priority: " + value);
    }
}

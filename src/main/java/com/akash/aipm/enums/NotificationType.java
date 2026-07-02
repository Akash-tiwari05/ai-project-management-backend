package com.akash.aipm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationType {
    INVITE("invite"),
    AI("ai"),
    TODO("todo"),
    PROJECT("project"),
    TEAM("team"),
    BILLING("billing");

    private final String value;

    NotificationType(String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static NotificationType from(String value) {
        for (NotificationType t : values()) {
            if (t.value.equalsIgnoreCase(value)) return t;
        }
        throw new IllegalArgumentException("Unknown notification type: " + value);
    }
}
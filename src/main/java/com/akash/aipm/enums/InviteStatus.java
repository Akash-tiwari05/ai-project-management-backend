package com.akash.aipm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InviteStatus {
    PENDING("pending"),
    ACCEPTED("accepted"),
    DECLINED("declined"),
    REVOKED("revoked"),
    EXPIRED("expired");

    private final String value;

    InviteStatus(String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static InviteStatus from(String value) {
        for (InviteStatus s : values()) {
            if (s.value.equalsIgnoreCase(value)) return s;
        }
        throw new IllegalArgumentException("Unknown invite status: " + value);
    }
}
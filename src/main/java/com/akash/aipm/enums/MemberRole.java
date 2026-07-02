package com.akash.aipm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MemberRole {
    OWNER("owner"),
    ADMIN("admin"),
    MEMBER("member"),
    VIEWER("viewer");

    private final String value;

    MemberRole(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MemberRole from(String value) {
        for (MemberRole r : values()) {
            if (r.value.equalsIgnoreCase(value)) return r;
        }
        throw new IllegalArgumentException("Unknown role: " + value);
    }

    public boolean isOwner() {
        return this == OWNER;
    }

    public boolean isAdmin() {
        return this == ADMIN;
    }

    public boolean canEdit() {
        return this == OWNER || this == ADMIN;
    }

    public boolean canDelete() {
        return this == OWNER;
    }

    public boolean canInvite() {
        return this != VIEWER;
    }

    public boolean canSendMessages() {
        return this != VIEWER;
    }

    public boolean canCreateTodos() {
        return this != VIEWER;
    }

    public boolean canManageMembers() {
        return this == OWNER || this == ADMIN;
    }

    public boolean hasHigherPrivilegeThan(MemberRole other) {
        return this.ordinal() < other.ordinal();
    }
}
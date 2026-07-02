package com.akash.aipm.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PlanType {

    FREE("free", 0, 2, 1000, 3),
    PRO("pro", 5, 7, 7000, 10),
    PRIME("prime", 15, 30, 20000, Integer.MAX_VALUE);

    private final String value;
    private final int monthlyPriceUsd;
    private final int maxProjects;
    private final int aiTokenLimit;
    private final int maxMembersPerProject;

    PlanType(String value, int monthlyPriceUsd, int maxProjects, int aiTokenLimit, int maxMembersPerProject) {
        this.value = value;
        this.monthlyPriceUsd = monthlyPriceUsd;
        this.maxProjects = maxProjects;
        this.aiTokenLimit = aiTokenLimit;
        this.maxMembersPerProject = maxMembersPerProject;
    }

    @JsonValue
    public String getValue() { return value; }
    public int getMonthlyPriceUsd() { return monthlyPriceUsd; }
    public int getMaxProjects() { return maxProjects; }
    public int getAiTokenLimit() { return aiTokenLimit; }
    public int getMaxMembersPerProject() { return maxMembersPerProject; }

    @JsonCreator
    public static PlanType from(String value) {
        for (PlanType p : values()) {
            if (p.value.equalsIgnoreCase(value)) return p;
        }
        throw new IllegalArgumentException("Unknown plan: " + value);
    }
}

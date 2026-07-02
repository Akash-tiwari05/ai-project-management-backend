package com.akash.aipm.entity;

import com.akash.aipm.enums.AiRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
        name = "ai_messages",
        indexes = {
                @Index(name = "idx_aimsg_project", columnList = "project_id"),
                @Index(name = "idx_aimsg_sender",  columnList = "sender_id")
        }
)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"project", "sender"})
@EqualsAndHashCode(callSuper = true, exclude = {"project", "sender"})
public class AiMessage extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AiRole role;

    @Column(length = 60)
    private String model;

    @Column(name = "prompt_tokens")
    private Integer promptTokens;

    @Column(name = "completion_tokens")
    private Integer completionTokens;

    @Column(name = "total_tokens")
    private Integer totalTokens;

    @Column(name = "latency_ms")
    private Integer latencyMs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    public boolean isUserMessage() {
        return role == AiRole.USER;
    }

    public boolean isAssistantMessage() {
        return role == AiRole.ASSISTANT;
    }

    public int getTotalCost() {
        return totalTokens != null ? totalTokens : 0;
    }
}
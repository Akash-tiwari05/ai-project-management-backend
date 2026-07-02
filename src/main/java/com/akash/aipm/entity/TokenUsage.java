package com.akash.aipm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(
        name = "token_usage",
        indexes = {
                @Index(name = "idx_tku_user_date", columnList = "user_id, date"),
                @Index(name = "idx_tku_date",      columnList = "date")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_token_usage_user_date",
                        columnNames = {"user_id", "date"}
                )
        }
)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"user"})
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
public class TokenUsage extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    @Column(name = "tokens_used", nullable = false)
    private int tokensUsed = 0;

    @Column(name = "requests_made", nullable = false)
    private int requestsMade = 0;

    public void increment(int tokens) {
        this.tokensUsed += tokens;
        this.requestsMade += 1;
    }

    public boolean isWithinLimit(int planLimit) {
        return tokensUsed < planLimit;
    }

    public int remaining(int planLimit) {
        return Math.max(0, planLimit - tokensUsed);
    }
}
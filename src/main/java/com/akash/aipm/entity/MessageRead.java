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

import java.time.Instant;

@Entity
@Table(
        name = "message_reads",
        indexes = {
                @Index(name = "idx_mread_message", columnList = "message_id"),
                @Index(name = "idx_mread_user",    columnList = "user_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_msg_read",
                        columnNames = {"message_id", "user_id"}
                )
        }
)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"message", "user"})
@EqualsAndHashCode(callSuper = true, exclude = {"message", "user"})
public class MessageRead extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private TeamMessage message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "read_at", nullable = false)
    private Instant readAt = Instant.now();
}
package com.akash.aipm.entity;

import com.akash.aipm.enums.InviteStatus;
import com.akash.aipm.enums.MemberRole;
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

import java.time.Instant;

@Entity
@Table(
        name = "invites",
        indexes = {
                @Index(name = "idx_inv_token",          columnList = "token",      unique = true),
                @Index(name = "idx_inv_project_status", columnList = "project_id, status"),
                @Index(name = "idx_inv_email",          columnList = "email")
        }
)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"project", "invitedBy"})
@EqualsAndHashCode(callSuper = true, exclude = {"project", "invitedBy"})
public class Invite extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_by_id", nullable = false)
    private User invitedBy;

    @Column(nullable = false, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MemberRole role = MemberRole.MEMBER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InviteStatus status = InviteStatus.PENDING;

    @Column(nullable = false, unique = true, length = 255)
    private String token;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "responded_at")
    private Instant respondedAt;

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public boolean isPending() {
        return status == InviteStatus.PENDING && !isExpired();
    }

    public void accept() {
        this.status = InviteStatus.ACCEPTED;
        this.respondedAt = Instant.now();
    }

    public void decline() {
        this.status = InviteStatus.DECLINED;
        this.respondedAt = Instant.now();
    }

    public void revoke() {
        this.status = InviteStatus.REVOKED;
    }
}
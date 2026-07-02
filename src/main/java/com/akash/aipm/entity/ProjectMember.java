package com.akash.aipm.entity;

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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
        name = "project_members",
        indexes = {
                @Index(name = "idx_pm_project", columnList = "project_id"),
                @Index(name = "idx_pm_user",    columnList = "user_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_project_member",
                        columnNames = {"project_id", "user_id"}
                )
        }
)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"project", "user"})
@EqualsAndHashCode(callSuper = true, exclude = {"project", "user"})
public class ProjectMember extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MemberRole role = MemberRole.MEMBER;

    // ── Business logic helpers ────────────────────────────────────────

    public boolean isOwner() {
        return role == MemberRole.OWNER;
    }

    public boolean isAdmin() {
        return role == MemberRole.ADMIN;
    }

    public boolean canEdit() {
        return role == MemberRole.OWNER || role == MemberRole.ADMIN;
    }

    public boolean canDelete() {
        return role == MemberRole.OWNER;
    }

    public boolean canInvite() {
        return role != MemberRole.VIEWER;
    }
}
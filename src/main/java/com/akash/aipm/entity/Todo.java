package com.akash.aipm.entity;

import com.akash.aipm.enums.Priority;
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
import java.time.LocalDate;

@Entity
@Table(
        name = "todos",
        indexes = {
                @Index(name = "idx_todos_project",  columnList = "project_id"),
                @Index(name = "idx_todos_assignee", columnList = "assignee_id"),
                @Index(name = "idx_todos_due",      columnList = "due_date")
        }
)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"project", "assignee", "createdBy"})
@EqualsAndHashCode(callSuper = true, exclude = {"project", "assignee", "createdBy"})
public class Todo extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private boolean done = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Priority priority;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(nullable = false)
    private int position = 0;

    @Column(name = "completed_at")
    private Instant completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    public void complete() {
        this.done = true;
        this.completedAt = Instant.now();
    }

    public void reopen() {
        this.done = false;
        this.completedAt = null;
    }

    public boolean isOverdue() {
        return !done && dueDate != null
                && LocalDate.now().isAfter(dueDate);
    }
}
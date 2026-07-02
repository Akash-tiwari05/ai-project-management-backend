package com.akash.aipm.entity;

import com.akash.aipm.enums.BillingCycle;
import com.akash.aipm.enums.PlanType;
import com.akash.aipm.enums.SubscriptionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.time.Instant;

@Entity
@Table(
        name = "subscriptions",
        indexes = {
                @Index(name = "idx_sub_user",   columnList = "user_id",                  unique = true),
                @Index(name = "idx_sub_rzp",    columnList = "razorpay_subscription_id"),
                @Index(name = "idx_sub_status", columnList = "status")
        }
)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"user"})
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
public class Subscription extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_id", nullable = false, length = 20)
    private PlanType planId = PlanType.FREE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "billing_cycle", nullable = false, length = 10)
    private BillingCycle billingCycle = BillingCycle.MONTHLY;

    @Column(name = "razorpay_subscription_id", length = 100)
    private String razorpaySubscriptionId;

    @Column(name = "razorpay_customer_id", length = 100)
    private String razorpayCustomerId;

    @Column(name = "current_period_start")
    private Instant currentPeriodStart;

    @Column(name = "current_period_end")
    private Instant currentPeriodEnd;

    @Column(name = "cancel_at_period_end", nullable = false)
    private boolean cancelAtPeriodEnd = false;

    @Column(name = "cancelled_at")
    private Instant cancelledAt;

    @Column(name = "trial_ends_at")
    private Instant trialEndsAt;

    // ── Business logic helpers ────────────────────────────────────────

    public boolean isActive() {
        return status == SubscriptionStatus.ACTIVE;
    }

    public boolean isCancelled() {
        return status == SubscriptionStatus.CANCELLED;
    }

    public boolean isPastDue() {
        return status == SubscriptionStatus.PAST_DUE;
    }

    public boolean isInTrial() {
        return trialEndsAt != null && Instant.now().isBefore(trialEndsAt);
    }

    public boolean hasExpired() {
        return currentPeriodEnd != null && Instant.now().isAfter(currentPeriodEnd);
    }

    public void cancel() {
        this.cancelAtPeriodEnd = true;
        this.cancelledAt = Instant.now();
    }

    public void activate(PlanType plan, String rzpSubscriptionId,
                         Instant periodStart, Instant periodEnd) {
        this.planId = plan;
        this.status = SubscriptionStatus.ACTIVE;
        this.razorpaySubscriptionId = rzpSubscriptionId;
        this.currentPeriodStart = periodStart;
        this.currentPeriodEnd = periodEnd;
        this.cancelAtPeriodEnd = false;
        this.cancelledAt = null;
    }

    public void downgradeToFree() {
        this.planId = PlanType.FREE;
        this.status = SubscriptionStatus.ACTIVE;
        this.razorpaySubscriptionId = null;
        this.razorpayCustomerId = null;
        this.currentPeriodStart = null;
        this.currentPeriodEnd = null;
        this.cancelAtPeriodEnd = false;
    }
}
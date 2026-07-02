# 🚀 Core System Architecture & Data Models (Spring Boot)

This repository houses the central data architecture, JPA entities, and database configurations for my Spring Boot application. It serves as the single source of truth for the entire database layer and application state.

## 📂 Project Structure

```text
├── config/              # Database, Security, and Beans configuration classes
├── entity/              # JPA / Hibernate Database Entities
│   ├── BaseEntity       # @MappedSuperclass with IDs and Auditing timestamps
│   ├── User             # Core user profiles & authentication data
│   ├── RefreshToken     # JWT auth session management & token rotation
│   ├── Project          # Workspace & workspace management
│   ├── ProjectMember    # Team member assignments & permissions mapping
│   ├── Invite           # Collaboration and project invitations
│   ├── Todo             # Task management & project action items
│   ├── TeamMessage      # Internal channel and group communications
│   ├── MessageRead      # Read-receipt tracking for user chats
│   ├── AiMessage        # Context logs for AI chat interactions
│   ├── TokenUsage       # AI LLM usage and API quota tracking
│   ├── Notification     # System events & user alert tracking
│   ├── Subscription     # Billing plans, tiers, and quotas
│   └── PaymentEvent     # Webhook logs & transactional history
└── enums/               # Java Enums for strict type safety
    ├── AiRole           # AI prompt contexts (SYSTEM, USER, ASSISTANT)
    ├── BillingCycle     # Payment frequencies (MONTHLY, YEARLY)
    ├── InviteStatus     # Lifecycle states (PENDING, ACCEPTED, DECLINED)
    ├── MemberRole       # Project access control (OWNER, ADMIN, VIEWER)
    ├── MessageType      # Content variants (TEXT, FILE, SYSTEM)
    ├── NotificationType # Delivery channels (EMAIL, PUSH, IN_APP)
    ├── PaymentStatus    # Checkout results (SUCCEEDED, PROCESSING, FAILED)
    ├── PlanType         # SaaS tier access levels (FREE, PREMIUM, ENTERPRISE)
    ├── Priority         # Task urgency (LOW, MEDIUM, HIGH)
    ├── ProjectStatus    # Workspace lifecycles (ACTIVE, COMPLETED, ARCHIVED)
    ├── SubscriptionStatus # Gateway account standing (ACTIVE, PAST_DUE, CANCELED)
    └── UserStatus       # Account verification states (ACTIVE, BANNED, UNVERIFIED)
```

## 🛠️ Key Architectural Features

### 🔹 Spring Data JPA Auditing (`BaseEntity`)
To keep the codebase **DRY (Don't Repeat Yourself)**, entities extend a centralized `BaseEntity`. This relies on Spring Data JPA's auditing framework to automatically handle lifecycle tracking without manual boilerplate:
* `@Id` / `@GeneratedValue` for UUID strategy handling.
* `@CreatedDate` & `@LastModifiedDate` for seamless audit trails.

### 🔹 Type-Safe Database Enums
Rather than relying on volatile string fields for system status, the system uses Java Enums combined with JPA's `@Enumerated(EnumType.STRING)` mapping. This ensures that only database-validated states are committed to the schema.

### 🔹 Feature-Rich Schema Set
* **SaaS Boilerplate Ready:** Full workspace structure with `Project`, `ProjectMember`, and Stripe-ready `PaymentEvent` tracking.
* **AI Integration:** Specialized schemas (`AiMessage`, `TokenUsage`) to audit context windows and trace LLM token consumption.
* **Real-time Collab:** Multi-user task allocation (`Todo`) alongside transactional message tracing.

## ⚙️ Requirements & Quick Setup

### Prerequisites
* Java 17 or higher
* Maven / Gradle

### Database Settings
Configure your profile credentials inside your `src/main/resources/application.yml` or `application.properties`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/your_db_name
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

package com.github.nfers.service.domain.entity;

import com.github.nfers.service.domain.entity.enums.NotificationTypeEnum;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "event_history")
public class EventHistoryEntity  extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private NotificationTypeEnum type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private SubscriptionEntity subscription;

    @Column(name = "subscription_id",  nullable = false)
    private String subscriptionId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public EventHistoryEntity() {
        //
    }

    public Long getId() {
        return id;
    }

    public EventHistoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public NotificationTypeEnum getType() {
        return type;
    }

    public EventHistoryEntity setType(NotificationTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public EventHistoryEntity setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public EventHistoryEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String toString() {
        return "EventHistoryEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", subscriptionId=" + subscriptionId +
                ", createdAt=" + createdAt +
                "} " + super.toString();
    }
}

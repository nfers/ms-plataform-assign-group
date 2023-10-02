package com.github.nfers.service.domain.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "subscription")
public class SubscriptionEntity extends PanacheEntityBase {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private StatusEntity status;

    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt = Instant.now();

    public SubscriptionEntity() {
        //
    }

    public String getId() {
        return id;
    }

    public SubscriptionEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public SubscriptionEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public SubscriptionEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Long getStatusId() {
        return statusId;
    }

    public SubscriptionEntity setStatusId(Long statusId) {
        this.statusId = statusId;
        return this;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public SubscriptionEntity setStatus(StatusEntity status) {
        this.status = status;
        return this;
    }

    public static SubscriptionEntity findById(String id) {
        return find("id", id).firstResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionEntity)) return false;
        SubscriptionEntity that = (SubscriptionEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getStatusId(), that.getStatusId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getStatusId(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "SubscriptionEntity{" + "id='" + id + '\'' + ", statusId=" + statusId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}

package com.github.nfers.service.domain.entity;

import com.github.nfers.service.domain.entity.enums.StatusEnum;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "status")
public class StatusEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", nullable = false)
    public StatusEnum name;

    public StatusEntity() {
    }

    public StatusEntity(Long id, StatusEnum name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public StatusEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public StatusEnum getName() {
        return name;
    }

    public StatusEntity setName(StatusEnum name) {
        this.name = name;
        return this;
    }

    public static StatusEntity findByName(StatusEnum name) {
        return find("name", name).firstResult();
    }

    public static StatusEntity findById(Long id) {
        return find("id", id).firstResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusEntity)) return false;
        StatusEntity that = (StatusEntity) o;
        return Objects.equals(getId(), that.getId()) && getName() == that.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
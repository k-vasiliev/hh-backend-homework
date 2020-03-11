package ru.hh.homework.at_least_some_backend.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
@TypeDef(name = "postgres_enum", typeClass = PostgresEnum.class)
public abstract class HHEntity
{
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at") @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at") @UpdateTimestamp
    private OffsetDateTime updatedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}

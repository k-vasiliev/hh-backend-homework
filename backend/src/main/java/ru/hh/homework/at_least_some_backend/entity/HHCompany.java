package ru.hh.homework.at_least_some_backend.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "hh_company")
public class HHCompany
{
    @Column(name = "hh_company_id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hh_company_name")
    private String name;

    @JoinColumn(name = "hh_user_id") @ManyToOne
    private HHUser user;

    @Column(name = "hh_company_creation_timestamp") @CreationTimestamp
    private OffsetDateTime creationDateTime;

    @Column(name = "hh_company_last_update_timestamp") @UpdateTimestamp
    private OffsetDateTime lastUpdateDateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public HHUser getUser() { return user; }
    public void setUser(HHUser userId) { this.user = userId; }

    public OffsetDateTime getCreationDateTime() { return creationDateTime; }
    public void setCreationDateTime(OffsetDateTime creationDateTime) { this.creationDateTime = creationDateTime; }

    public OffsetDateTime getLastUpdateDateTime() { return lastUpdateDateTime; }
    public void setLastUpdateDateTime(OffsetDateTime lastUpdateDateTime) { this.lastUpdateDateTime = lastUpdateDateTime; }
}

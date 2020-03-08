package ru.hh.homework.at_least_some_backend.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "hh_user")
public class HHUser
{
    @Column(name = "hh_user_id") @Id @GeneratedValue
    private Long id;

    @Column(name = "hh_user_name")
    private String name;

    @Column(name = "hh_user_type") @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "hh_user_creation_timestamp")
    private OffsetDateTime creationDateTime;

    @Column(name = "hh_user_last_update_timestamp")
    private OffsetDateTime lastUpdateDateTime;

    public enum UserType { EMPLOYER, APPLICANT }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UserType getType() { return type; }
    public void setType(UserType type) { this.type = type; }

    public OffsetDateTime getCreationDateTime() { return creationDateTime; }
    public void setCreationDateTime(OffsetDateTime creationDateTime) { this.creationDateTime = creationDateTime; }

    public OffsetDateTime getLastUpdateDateTime() { return lastUpdateDateTime; }
    public void setLastUpdateDateTime(OffsetDateTime lastUpdateDateTime) { this.lastUpdateDateTime = lastUpdateDateTime; }
}

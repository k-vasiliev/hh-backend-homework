package ru.hh.homework.at_least_some_backend.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "hh_user")
public class HHUser extends HHEntity
{
    @Column(name = "name")
    private String name;

    @Column(name = "type") @Enumerated(EnumType.STRING) @Type(type = "postgres_enum")
    private UserType type;

    public enum UserType { EMPLOYER, APPLICANT }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UserType getType() { return type; }
    public void setType(UserType type) { this.type = type; }
}

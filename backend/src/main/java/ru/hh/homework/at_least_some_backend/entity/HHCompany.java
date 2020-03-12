package ru.hh.homework.at_least_some_backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "hh_company")
public class HHCompany extends HHEntity
{
    @Column(name = "name")
    private String name;

    @JoinColumn(name = "user_id") @ManyToOne
    private HHUser user;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public HHUser getUser() { return user; }
    public void setUser(HHUser userId) { this.user = userId; }
}

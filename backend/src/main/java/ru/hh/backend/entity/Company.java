package ru.hh.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Company() {
    }

    public Company(String name, User user, LocalDate date) {
        this.name = name;
        this.user = user;
        super.creationDate = date;
        super.lastUpdateDate = date;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

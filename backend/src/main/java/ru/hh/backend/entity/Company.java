package ru.hh.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Company() {
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

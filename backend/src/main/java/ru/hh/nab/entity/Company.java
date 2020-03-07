package ru.hh.nab.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    public Company(User users, String name, LocalDate lastUpdate, boolean active) {
        this.users = users;
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.active = active;
    }

    public Company() {
    }

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vacancy> vacancies = new HashSet<>();

    public void addVacancy(Vacancy vacancy) {
        this.vacancies.add(vacancy);
        vacancy.setCompany(this);
    }

    public void removeVacancy(Vacancy vacancy) {
        this.vacancies.remove(vacancy);
        vacancy.setCompany(null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int companyId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;

    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public boolean isActive() {
        return active;
    }

    public User getUser() {
        return users;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUser(User users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return companyId == company.companyId &&
                active == company.active &&
                Objects.equals(name, company.name) &&
                Objects.equals(lastUpdate, company.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, name, lastUpdate, active);
    }
}

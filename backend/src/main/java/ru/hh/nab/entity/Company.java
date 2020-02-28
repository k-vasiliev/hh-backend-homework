package ru.hh.nab.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    public Company(int compId, String name, Date lastUpdate, boolean active) {
        this.compId = compId;
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
    private int compId;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update")
    private Date lastUpdate;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    /*@ManyToOne
    @JoinTable(name = "user", joinColumns = @JoinColumn(name = "user_id"))
    private int userId;*/

    public int getCompId() {
        return compId;
    }

    public String getName() {
        return name;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public boolean isActive() {
        return active;
    }

    public Users getUser() {
        return users;
    }

    /*public int getUserId() {
        return userId;
    }*/

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    /*public void setUserId(int userId) {
        this.userId = userId;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return compId == company.compId &&
                active == company.active &&
                Objects.equals(name, company.name) &&
                Objects.equals(lastUpdate, company.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compId, name, lastUpdate, active);
    }
}

package ru.hh.nab.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "vacancy")
public class Vacancy {

    public Vacancy(Company company, String heading, int salary, String description, String contacts, boolean active, Date last_update) {
        this.company = company;
        this.heading = heading;
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
        this.active = active;
        this.last_update = last_update;
    }

    public Vacancy() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vac_id")
    private int vacId;

    @Column(name = "heading")
    private String heading;

    @Column(name = "salary")
    private int salary;

    @Column(name = "description")
    private String description;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "active")
    private boolean active;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update")
    private Date last_update;

    public Company getCompany() {
        return company;
    }

    public int getVacId() {
        return vacId;
    }

    public String getHeading() {
        return heading;
    }

    public int getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }

    public boolean isActive() {
        return active;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setVacId(int vacId) {
        this.vacId = vacId;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return vacId == vacancy.vacId &&
                salary == vacancy.salary &&
                active == vacancy.active &&
                Objects.equals(heading, vacancy.heading) &&
                Objects.equals(description, vacancy.description) &&
                Objects.equals(contacts, vacancy.contacts) &&
                Objects.equals(last_update, vacancy.last_update);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacId, heading, salary, description, contacts, active, last_update);
    }
}

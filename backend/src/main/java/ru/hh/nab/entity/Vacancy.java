package ru.hh.nab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vacancy")
public class Vacancy {

    public Vacancy(Company company, String header, int salary, String description,
                   String contacts, boolean active, LocalDate lastUpdate) {
        this.company = company;
        this.header = header;
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
        this.active = active;
        this.lastUpdate = lastUpdate;
    }

    public Vacancy() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Negotiation> negotiations = new HashSet<>();

    public void addNegotiation(Negotiation negotiation) {
        this.negotiations.add(negotiation);
        negotiation.setVacancy(this);
    }

    public void removeNegotiation(Negotiation negotiation) {
        this.negotiations.remove(negotiation);
        negotiation.setVacancy(null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id")
    private int vacId;

    @Column(name = "header")
    private String header;

    @Column(name = "salary")
    private int salary;

    @Column(name = "description")
    private String description;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "active")
    private boolean active;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "last_update")
    private LocalDate lastUpdate;

    public Company getCompany() {
        return company;
    }

    public int getVacId() {
        return vacId;
    }

    public String getHeader() {
        return header;
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

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setVacId(int vacId) {
        this.vacId = vacId;
    }

    public void setHeader(String header) {
        this.header = header;
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

    public void setLast_update(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return vacId == vacancy.vacId &&
                salary == vacancy.salary &&
                active == vacancy.active &&
                Objects.equals(header, vacancy.header) &&
                Objects.equals(description, vacancy.description) &&
                Objects.equals(contacts, vacancy.contacts) &&
                Objects.equals(lastUpdate, vacancy.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacId, header, salary, description, contacts, active, lastUpdate);
    }
}

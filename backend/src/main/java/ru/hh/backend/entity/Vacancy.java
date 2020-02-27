package ru.hh.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "vacancy")
public class Vacancy extends Model {
    @Column(name = "vacancy_title")
    private String vacancyTitle;

    @Column(name = "compensation")
    private Integer compensation;

    @Column(name = "desccription")
    private String description;

    @Column(name = "contacts")
    private String contacts;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Vacancy(String vacancyTitle, Integer compensation, String description, String contacts, Company company) {
        this.vacancyTitle = vacancyTitle;
        this.compensation = compensation;
        this.description = description;
        this.contacts = contacts;
        this.company = company;
    }

    public String getVacancyTitle() {
        return vacancyTitle;
    }

    public void setVacancyTitle(String vacancyTitle) {
        this.vacancyTitle = vacancyTitle;
    }

    public Integer getCompensation() {
        return compensation;
    }

    public void setCompensation(Integer compensation) {
        this.compensation = compensation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

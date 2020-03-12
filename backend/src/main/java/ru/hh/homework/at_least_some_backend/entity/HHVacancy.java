package ru.hh.homework.at_least_some_backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "hh_vacancy")
public class HHVacancy extends HHEntity
{
    @Column(name = "title")
    private String title;

    @JoinColumn(name = "company_id") @ManyToOne
    private HHCompany company;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "description")
    private String description;

    @Column(name = "contacts")
    private String contacts;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public HHCompany getCompany() { return company; }
    public void setCompany(HHCompany company) { this.company = company; }

    public Long getSalary() { return salary; }
    public void setSalary(Long salary) { this.salary = salary; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContacts() { return contacts; }
    public void setContacts(String contacts) { this.contacts = contacts; }
}

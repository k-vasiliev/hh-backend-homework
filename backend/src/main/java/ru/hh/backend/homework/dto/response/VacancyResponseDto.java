package ru.hh.backend.homework.dto.response;

import ru.hh.backend.homework.entity.CompanyEntity;
import ru.hh.backend.homework.entity.NegotiationEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class VacancyResponseDto {
    private Long id;

    private String title;

    private CompanyEntity company;

    private Integer salary;

    private String description;

    private String contacts;

    private Date dateCreate;


    public VacancyResponseDto(Long id, String title, CompanyEntity company, Integer compensation, String description, String contacts, Date dateCreate) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.salary = compensation;
        this.description = description;
        this.contacts = contacts;
        this.dateCreate = dateCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}

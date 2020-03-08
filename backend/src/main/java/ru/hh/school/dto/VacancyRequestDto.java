package ru.hh.school.dto;

import ru.hh.school.entity.CompanyEntity;

public class VacancyRequestDto {
    private String title;
    private CompanyEntity companyEntity;
    private Integer salary;
    private String description;
    private String contacts;

    public VacancyRequestDto(String title, CompanyEntity companyEntity, Integer salary, String description, String contacts) {
        this.title = title;
        this.companyEntity = companyEntity;
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
    }

    public String getTitle() {
        return title;
    }



    public Integer getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }
}

package ru.hh.school.dto;

import java.math.BigInteger;

public class VacancyPopupResponseDto {

    private Integer id;
    private BigInteger salary;
    private String description;
    private String contacts;
    private CompanyResponseDto company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getSalary() {
        return salary;
    }

    public void setSalary(BigInteger salary) {
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

    public CompanyResponseDto getCompany() {
        return company;
    }

    public void setCompany(CompanyResponseDto company) {
        this.company = company;
    }
}

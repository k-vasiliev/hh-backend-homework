package ru.hh.school.dto;

import java.math.BigInteger;

public class VacancyRequestDto {

    private Integer companyId;
    private String title;
    private BigInteger compensation;
    private String description;
    private String contacts;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigInteger getCompensation() {
        return compensation;
    }

    public void setCompensation(BigInteger compensation) {
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
}

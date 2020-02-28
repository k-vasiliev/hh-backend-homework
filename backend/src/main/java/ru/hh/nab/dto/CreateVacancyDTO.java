package ru.hh.nab.dto;

import javax.validation.constraints.NotNull;

public class CreateVacancyDTO {
    @NotNull
    private String title;
    @NotNull
    private String companyId;
    @NotNull
    private String salary;
    @NotNull
    private String description;
    @NotNull
    private String contacts;

    public String getTitle() {
        return title;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getSalary() {
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

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}

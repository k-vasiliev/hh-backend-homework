package ru.hh.nab.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateVacancyDTO {

    @NotNull
    @Size(min = 1, max = 250, message = "Vacancy title must be between 1 and 250 characters")
    private String title;

    @NotNull
    private String companyId;

    @NotNull
    private String salary;

    @NotNull
    @Size(min = 1, max = 250, message = "Vacancy description must be between 1 and 250 characters")
    private String description;

    @NotNull
    @Size(min = 1, max = 250, message = "Vacancy contacts must be between 1 and 250 characters")
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

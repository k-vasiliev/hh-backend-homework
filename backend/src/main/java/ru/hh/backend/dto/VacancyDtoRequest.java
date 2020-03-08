package ru.hh.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VacancyDtoRequest {
    @NotNull
    @Size(min = 1, max = 150)
    private String title;
    @NotNull
    private Integer companyId;
    @NotNull
    private Integer salary;
    @NotNull
    private String description;
    @NotNull
    @Size(min = 0, max = 50)
    private String contacts;

    public VacancyDtoRequest() {
    }

    public String getTitle() {
        return title;
    }

    public Integer getCompanyId() {
        return companyId;
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
}

package ru.hh.school.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

public class VacancyRequestDto {
    @NotNull
    private Integer companyId;
    @NotNull
    @Size(min = 1, max = 256)
    private String title;
    private BigInteger salary;
    private String description;
    @NotNull
    private String contacts;

    public Integer getCompanyId() {
        return companyId;
    }

    public String getTitle() {
        return title;
    }

    public BigInteger getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }

}

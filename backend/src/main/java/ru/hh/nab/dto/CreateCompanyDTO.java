package ru.hh.nab.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateCompanyDTO {

    @NotNull
    @Size(min = 1, max = 250, message = "Company name must be between 1 and 250 characters")
    private String name;

    @NotNull
    private String userId;

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

package ru.hh.nab.dto;

import javax.validation.constraints.NotNull;

public class CreateCompanyDTO {
    @NotNull
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

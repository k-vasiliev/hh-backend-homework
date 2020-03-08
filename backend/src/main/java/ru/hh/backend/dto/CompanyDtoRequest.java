package ru.hh.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyDtoRequest {
    @NotNull
    @Size(min = 1, max = 150)
    private String name;
    @NotNull
    private Integer userId;

    public CompanyDtoRequest() {
    }

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }
}

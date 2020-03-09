package ru.hh.school.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyRequestDto {
    @NotNull
    @Size(min = 1, max = 256)
    private String name;
    @NotNull
    private Integer userId;

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }

}

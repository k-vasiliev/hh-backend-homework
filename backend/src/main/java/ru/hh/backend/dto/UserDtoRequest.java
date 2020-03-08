package ru.hh.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDtoRequest {
    @NotNull
    @Size(min = 1, max = 150)
    private String name;
    @NotNull
    private String type;

    public UserDtoRequest() {
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}

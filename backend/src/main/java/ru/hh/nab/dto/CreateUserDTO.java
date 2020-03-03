package ru.hh.nab.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserDTO {

    @NotNull
    @Size(min = 1, max = 250, message = "User name must be between 1 and 250 characters")
    private String name;

    @NotNull
    @Size(min = 5, max = 50, message = "User name must be between 5 and 250 characters")
    private String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package ru.hh.nab.dto;

import javax.validation.constraints.NotNull;

public class CreateUserDTO {
    @NotNull
    private String name;
    @NotNull
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

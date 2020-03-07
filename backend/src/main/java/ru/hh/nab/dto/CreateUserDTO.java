package ru.hh.nab.dto;

import ru.hh.nab.entity.UserType;
import ru.hh.nab.util.Utils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserDTO {

    @NotNull
    @Size(min = 1, max = 250, message = "User name must be between 1 and 250 characters")
    private String name;

    @NotNull
    @Size(min = 5, max = 50, message = "User name must be between 5 and 250 characters")
    private UserType type;

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = Utils.setType(type);
    }
}

package ru.hh.school.dto.request;

import ru.hh.school.entity.UserType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestDto {
    @NotNull
    @Size(min = 1, max = 256)
    private String name;
    @NotNull
    @Size(min = 1, max = 16)
    private UserType type;

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

}

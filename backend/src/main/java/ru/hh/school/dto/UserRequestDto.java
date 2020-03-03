package ru.hh.school.dto;

import ru.hh.school.entity.UserType;

public class UserRequestDto {

    private String name;
    private UserType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}

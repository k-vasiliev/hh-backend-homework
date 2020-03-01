package ru.hh.school.dto;

import ru.hh.school.entity.UserType;

public class UserRequestDto {

    private String name;
    private UserType userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

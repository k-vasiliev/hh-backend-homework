package ru.hh.backend.homework.dto;

import ru.hh.backend.homework.enums.UserType;

public class UserRequestDto {
    private String name;
    private UserType userType;

    public UserRequestDto() {
    }

    public UserRequestDto(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

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

package ru.hh.backend.homework.dto.request;

import ru.hh.backend.homework.enums.UserType;

public class UserRequestDto {
    private String name;
    private UserType userType;

    public UserRequestDto(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }
}

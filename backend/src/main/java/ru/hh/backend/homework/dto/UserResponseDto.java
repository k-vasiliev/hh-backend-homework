package ru.hh.backend.homework.dto.response;

public class UserResponseDto {
    private Integer userId;
    private String name;

    public UserResponseDto(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

package ru.hh.backend.homework.dto;

public class UserResponseDto {
    private Integer userId;
    private String name;

    public UserResponseDto() {
    }

    public UserResponseDto(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

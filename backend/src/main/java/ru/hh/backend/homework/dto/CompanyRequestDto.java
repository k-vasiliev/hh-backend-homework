package ru.hh.backend.homework.dto;

public class CompanyRequestDto {
    private String name;
    private Integer userId;

    public CompanyRequestDto(String name, Integer userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }
}

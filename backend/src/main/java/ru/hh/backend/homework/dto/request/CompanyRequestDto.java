package ru.hh.backend.homework.dto.request;

public class CompanyRequestDto {
    private String name;
    private Integer userId;

    public CompanyRequestDto(String name, Integer userId) {
        this.name = name;
        this.userId = userId;
    }
}

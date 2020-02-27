package ru.hh.backend.homework.dto.response;

public class CompanyResponseDto {
    private Integer companyId;
    private String name;

    public CompanyResponseDto(Integer companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }
}

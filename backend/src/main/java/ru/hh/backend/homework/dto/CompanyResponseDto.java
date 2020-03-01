package ru.hh.backend.homework.dto;

public class CompanyResponseDto {
    private Integer companyId;
    private String name;

    public CompanyResponseDto() {
    }

    public CompanyResponseDto(Integer companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

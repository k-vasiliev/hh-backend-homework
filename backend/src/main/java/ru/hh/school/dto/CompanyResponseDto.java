package ru.hh.school.dto;

public class CompanyResponseDto {

    private Long companyId;
    private String name;


    public CompanyResponseDto(Long companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package ru.hh.backend.dto.response;

public class CompanyResponseDto {

    public CompanyResponseDto(Long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    private Long id;

    private String companyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

package ru.hh.nab.dto;

import ru.hh.nab.entity.Company;

public class ResponseCompanyDTO {

    private Company company;

    private String name;

    private Integer companyId;

    public ResponseCompanyDTO(Company company) {
        this.company = company;
    }

    public ResponseCompanyDTO(String name, Integer companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    public ResponseCompanyDTO build() {
        this.name = this.company.getName();
        this.companyId = this.company.getCompId();
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}

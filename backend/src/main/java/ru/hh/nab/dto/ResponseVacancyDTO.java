package ru.hh.nab.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ResponseVacancyDTO {

    private String header;

    private String companyName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate lastUpdate;

    private ResponseCompanyDTO company;

    public ResponseVacancyDTO(String header, LocalDate lastUpdate, String companyName, Integer companyId) {
        this.header = header;
        this.companyName = companyName;
        this.lastUpdate = lastUpdate;
        this.company = new ResponseCompanyDTO(companyName, companyId);
    }

    public String getHeader() {
        return header;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public ResponseCompanyDTO getCompany() {
        return company;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setCompany(ResponseCompanyDTO company) {
        this.company = company;
    }
}

package ru.hh.school.dto.response;

import java.math.BigInteger;

public class VacancyPopupResponseDto extends VacancyResponseDto {

    private BigInteger salary;
    private String description;
    private String contacts;

    public VacancyPopupResponseDto(Integer id, String title, String dateCreate, CompanyResponseDto company, BigInteger salary, String description, String contacts) {
        super(id, title, dateCreate, company);
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
    }

    public BigInteger getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }

}

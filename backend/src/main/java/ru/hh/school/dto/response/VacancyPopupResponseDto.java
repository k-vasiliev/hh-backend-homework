package ru.hh.school.dto.response;

import java.math.BigInteger;

public class VacancyPopupResponseDto extends VacancyResponseDto {

    private BigInteger salary;
    private String description;
    private String contacts;

    public BigInteger getSalary() {
        return salary;
    }

    public void setSalary(BigInteger salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}

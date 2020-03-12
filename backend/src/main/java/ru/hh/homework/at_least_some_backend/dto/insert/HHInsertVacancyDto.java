package ru.hh.homework.at_least_some_backend.dto.insert;

import ru.hh.homework.at_least_some_backend.entity.HHVacancy;

public class HHInsertVacancyDto extends InsertDto<HHVacancy>
{
    private String title;
    private Long companyId;
    private Long salary;
    private String description;
    private String contacts;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public Long getSalary() { return salary; }
    public void setSalary(Long salary) { this.salary = salary; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContacts() { return contacts; }
    public void setContacts(String contacts) { this.contacts = contacts; }
}

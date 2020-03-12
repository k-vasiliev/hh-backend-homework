package ru.hh.homework.at_least_some_backend.dto.query;

import ru.hh.homework.at_least_some_backend.entity.HHVacancy;

public class HHQueryVacancyBodyDto
{
    private String title;
    private HHQueryCompanyDto company;
    private Long salary;
    private String description;
    private String contacts;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public HHQueryCompanyDto getCompany() { return company; }
    public void setCompany(HHQueryCompanyDto company) { this.company = company; }

    public Long getSalary() { return salary; }
    public void setSalary(Long salary) { this.salary = salary; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContacts() { return contacts; }
    public void setContacts(String contacts) { this.contacts = contacts; }

    public static HHQueryVacancyBodyDto fromEntity(HHVacancy entity)
    {
        if (entity == null) return null;

        var dto = new HHQueryVacancyBodyDto();

        dto.setTitle(entity.getTitle());
        dto.setCompany(HHQueryCompanyDto.fromEntity(entity.getCompany()));
        dto.setSalary(entity.getSalary());
        dto.setDescription(entity.getDescription());
        dto.setContacts(entity.getContacts());

        return dto;
    }
}

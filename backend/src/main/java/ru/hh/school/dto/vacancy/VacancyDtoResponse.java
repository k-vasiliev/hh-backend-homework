package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.dto.area.AreaDto;
import ru.hh.school.dto.company.CompanyDtoResponse;
import ru.hh.school.dto.salary.SalaryDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyDtoResponse {
    private Integer id;
    private String name;
    private AreaDto area;
    private SalaryDto salary;
    private String created_at;
    private CompanyDtoResponse employer;

    public VacancyDtoResponse(Integer id, String name, AreaDto area, SalaryDto salary, String created_at, CompanyDtoResponse employer) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.salary = salary;
        this.created_at = created_at;
        this.employer = employer;
    }

    public VacancyDtoResponse() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AreaDto getArea() {
        return area;
    }

    public SalaryDto getSalary() {
        return salary;
    }

    public String getCreated_at() {
        return created_at;
    }

    public CompanyDtoResponse getEmployer() {
        return employer;
    }

    @Override
    public String toString() {
        return "CompanyDtoResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}

package ru.hh.school.dto;

public class VacancyResponseDto {

    private Integer id;
    private String title;
    private String dateCreate;
    private CompanyResponseDto company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public CompanyResponseDto getCompany() {
        return company;
    }

    public void setCompany(CompanyResponseDto company) {
        this.company = company;
    }
}

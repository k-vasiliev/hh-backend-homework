package ru.hh.school.dto.response;

public class VacancyResponseDto {

    private Integer id;
    private String title;
    private String dateCreate;
    private CompanyResponseDto company;

    public VacancyResponseDto(Integer id, String title, String dateCreate, CompanyResponseDto company) {
        this.id = id;
        this.title = title;
        this.dateCreate = dateCreate;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public CompanyResponseDto getCompany() {
        return company;
    }

}

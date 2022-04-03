package ru.hh.school.resource.dto;

public class VacancyEmployerResponseDto {
    private Long id; // идентификатор компании
    private String name; // название комопании

    public VacancyEmployerResponseDto() {
    }

    public VacancyEmployerResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

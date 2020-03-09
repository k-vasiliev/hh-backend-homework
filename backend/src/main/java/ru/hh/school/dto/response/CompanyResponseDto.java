package ru.hh.school.dto.response;

public class CompanyResponseDto {

    private String name;
    private Integer id;

    public CompanyResponseDto(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

}

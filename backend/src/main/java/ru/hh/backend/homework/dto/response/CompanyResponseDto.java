package ru.hh.backend.homework.dto.response;

public class CompanyResponseDto {
    private Long id;

    private String name;

    public CompanyResponseDto(Long id, String companyName) {
        this.id = id;
        this.name = companyName;
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

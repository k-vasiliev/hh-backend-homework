package ru.hh.school.dto.area;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaDto {
    private Integer id;
    private String name;

    public AreaDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public AreaDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AreaDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

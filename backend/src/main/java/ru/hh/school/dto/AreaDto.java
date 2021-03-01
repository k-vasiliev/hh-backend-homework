package ru.hh.school.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

public class AreaDto {

    private Integer id;
    private String name;

    public AreaDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

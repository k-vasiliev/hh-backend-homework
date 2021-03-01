package ru.hh.school.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class AreaEntity {

    private Integer id;
    private String name;

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

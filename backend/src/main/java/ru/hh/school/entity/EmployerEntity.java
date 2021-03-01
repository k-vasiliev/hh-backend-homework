package ru.hh.school.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employer")
public class EmployerEntity {

    private String id;
    private String name;
    private String description;
    private AreaEntity area;

    public EmployerEntity() {

    }

    public EmployerEntity(String id, String name, String description, AreaEntity area) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AreaEntity getArea() {
        return area;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArea(AreaEntity area) {
        this.area = area;
    }
}

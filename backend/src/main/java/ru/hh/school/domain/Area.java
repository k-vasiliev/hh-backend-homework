package ru.hh.school.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class Area extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

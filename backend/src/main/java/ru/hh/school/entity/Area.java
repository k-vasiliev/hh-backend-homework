package ru.hh.school.entity;

import javax.persistence.*;

@Entity
@Table(name = "areas")
public class Area {
    @Column(name = "area_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer newId) {
        this.id = newId;
    }
}

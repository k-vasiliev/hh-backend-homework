package ru.hh.school.entity;

import javax.persistence.*;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "area_name")
    private String name;

    public Area(Integer areaId, String name) {
        this.areaId = areaId;
        this.name = name;
    }

    public Area() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

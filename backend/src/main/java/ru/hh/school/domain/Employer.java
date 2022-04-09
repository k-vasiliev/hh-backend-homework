package ru.hh.school.domain;

import javax.persistence.*;

@Entity
@Table(name = "employer")
public class Employer extends BaseEntity {
    private String name; // название комопании

    @Column(columnDefinition = "TEXT")
    private String description; // описание компании

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    public Employer() {
    }

    public Employer(String name, String description, Area area) {
        this.name = name;
        this.description = description;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}

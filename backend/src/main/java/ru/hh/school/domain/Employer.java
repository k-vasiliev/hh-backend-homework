package ru.hh.school.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employer")
public class Employer extends BaseEntity {
    private String name; // название комопании

    @Column(columnDefinition = "TEXT")
    private String description; // описание компании

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Long areaId;

    public Employer() {
    }

    public Employer(String name, String description, Long areaId) {
        this.name = name;
        this.description = description;
        this.areaId = areaId;
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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}

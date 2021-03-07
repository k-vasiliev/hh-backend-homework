package ru.hh.school.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employer")
public class EmployerEntity {

    public EmployerEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private String id;

    @Column(name = "creation")
    private LocalDateTime creation;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "employer")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private AreaEntity area;

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

package ru.hh.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "vacancies")
@Data
@EqualsAndHashCode(callSuper = true)
public class Vacancy extends BaseEntity{

    @Column
    private String title;

    @Column
    private Integer salary;

    @Column
    private String description;

    @Column
    private String contacts;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}

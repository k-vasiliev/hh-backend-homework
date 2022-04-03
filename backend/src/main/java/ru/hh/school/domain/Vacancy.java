package ru.hh.school.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Vacancy extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "role", nullable = false)
    private String role;
}

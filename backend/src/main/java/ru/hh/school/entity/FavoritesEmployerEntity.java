package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

public class FavoritesEmployerEntity {

    public FavoritesEmployerEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private String id;

    @Column(name = "creation")
    private LocalDateTime creation;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "comment")
    private String comment;

    private enum popularity {
        POPULAR, REGULAR
    }
}

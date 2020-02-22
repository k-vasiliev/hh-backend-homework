package ru.hh.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Negotiation extends BaseEntity {

    @ManyToOne
    private Resume resume;

    @ManyToOne
    private Vacancy vacancy;
}

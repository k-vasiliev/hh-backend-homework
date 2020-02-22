package ru.hh.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Vacancy extends BaseEntity{

    @ManyToOne
    private Company company;

    @Column
    private String title;

    @Column
    private Integer salary;

    @Column
    private String description;

    @Column
    private String contacts;

    @OneToMany(mappedBy = "vacancy")
    private List<Negotiation> negotiations;
}

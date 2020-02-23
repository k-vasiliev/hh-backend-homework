package ru.hh.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "companies")
@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity{

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User employer;
}

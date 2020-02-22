package ru.hh.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Vacancy extends BaseEntity{

    private Company company;

    private String title;

    private Integer salary;

    private String description;

    private String contacts;

    private List<Negotiation> negotiations;
}

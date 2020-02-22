package ru.hh.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Resume extends BaseEntity{

    @Column
    private String title;

    @Column
    private String workExperience;

    @Column
    private String contacts;

    @ManyToOne
    private User applicant;
}

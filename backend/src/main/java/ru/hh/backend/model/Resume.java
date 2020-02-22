package ru.hh.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Resume extends BaseEntity{

    private User applicant;

    private String title;

    private String workExperience;

    private String contacts;
}

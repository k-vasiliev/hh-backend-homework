package ru.hh.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Negotiation extends BaseEntity{

    private Resume resume;
}

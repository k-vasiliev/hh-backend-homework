package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalaryDto {

    @JsonProperty(required = true)
    private int from;
    @JsonProperty(required = true)
    private int to;
    @JsonProperty(required = true)
    private String currency;
    @JsonProperty(required = true)
    private Boolean gross;
}

package ru.hh.backend.dto;

import javax.validation.constraints.NotNull;

public class NegotiationDtoRequest {
    @NotNull
    private Integer resumeId;
    @NotNull
    private Integer vacancyId;

    public NegotiationDtoRequest() {
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }
}

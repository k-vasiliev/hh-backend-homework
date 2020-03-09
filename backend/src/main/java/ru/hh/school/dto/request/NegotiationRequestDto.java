package ru.hh.school.dto.request;

import javax.validation.constraints.NotNull;

public class NegotiationRequestDto {
    @NotNull
    private Integer resumeId;
    @NotNull
    private Integer vacancyId;

    public Integer getResumeId() {
        return resumeId;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

}

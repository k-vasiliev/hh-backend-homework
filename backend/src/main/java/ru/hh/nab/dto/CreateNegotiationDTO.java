package ru.hh.nab.dto;

import javax.validation.constraints.NotNull;

public class CreateNegotiationDTO {

    @NotNull
    private int resumeId;

    @NotNull
    private int vacancyId;

    public int getResumeId() {
        return resumeId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }
}

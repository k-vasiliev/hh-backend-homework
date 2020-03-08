package ru.hh.school.dto;

import ru.hh.school.entity.ResumeEntity;
import ru.hh.school.entity.VacancyEntity;

public class NegotiationRequestDto {
    private ResumeEntity resumeEntity;
    private VacancyEntity vacancyEntity;

    public NegotiationRequestDto(ResumeEntity resumeEntity, VacancyEntity vacancyEntity) {
        this.resumeEntity = resumeEntity;
        this.vacancyEntity = vacancyEntity;
    }

    public ResumeEntity getResumeEntity() {
        return resumeEntity;
    }

    public void setResumeEntity(ResumeEntity resumeEntity) {
        this.resumeEntity = resumeEntity;
    }

    public VacancyEntity getVacancyEntity() {
        return vacancyEntity;
    }

    public void setVacancyEntity(VacancyEntity vacancyEntity) {
        this.vacancyEntity = vacancyEntity;
    }
}

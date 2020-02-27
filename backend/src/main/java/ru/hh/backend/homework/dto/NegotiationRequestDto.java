package ru.hh.backend.homework.dto;

public class NegotiationRequestDto {
    private Integer resumeId;
    private Integer vacancyId;

    public NegotiationRequestDto(Integer resumeId, Integer vacancyId) {
        this.resumeId = resumeId;
        this.vacancyId = vacancyId;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }
}

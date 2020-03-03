package ru.hh.backend.dto;

public class NegotiationDtoRequest {

    private Integer resumeId;
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

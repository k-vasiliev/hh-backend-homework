package ru.hh.backend.dto;

public class NegotiationDtoResponse {

    private Integer id;
    private Integer vacancyId;
    private ResumeDtoResponse resume;

    public NegotiationDtoResponse(Integer id, Integer vacancyId, ResumeDtoResponse resume) {
        this.id = id;
        this.vacancyId = vacancyId;
        this.resume = resume;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public ResumeDtoResponse getResume() {
        return resume;
    }
}

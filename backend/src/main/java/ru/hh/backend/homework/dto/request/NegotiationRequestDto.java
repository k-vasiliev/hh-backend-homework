package ru.hh.backend.homework.dto.request;

public class NegotiationRequestDto {
    private Integer resumeId;
    private Integer vacancyId;

    public NegotiationRequestDto(Integer resumeId, Integer vacancyId) {
        this.resumeId = resumeId;
        this.vacancyId = vacancyId;
    }
}

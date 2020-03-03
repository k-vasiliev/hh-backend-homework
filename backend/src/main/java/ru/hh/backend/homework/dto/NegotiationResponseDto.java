package ru.hh.backend.homework.dto;

import ru.hh.backend.homework.entity.ResumeEntity;

public class NegotiationResponseDto {
    private Integer negotiationId;
    private ResumeResponseDto resume;

    public NegotiationResponseDto() {
    }

    public NegotiationResponseDto(Integer negotiationId, ResumeResponseDto resume) {
        this.negotiationId = negotiationId;
        this.resume = resume;
    }

    public Integer getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Integer negotiationId) {
        this.negotiationId = negotiationId;
    }

    public ResumeResponseDto getResume() {
        return resume;
    }

    public void setResume(ResumeResponseDto resume) {
        this.resume = resume;
    }
}

package ru.hh.backend.homework.dto;

import ru.hh.backend.homework.entity.ResumeEntity;

import java.util.Optional;

public class NegotiationResponseDto {
    private Integer negotiationId;
    private ResumeEntity resume;

    public NegotiationResponseDto() {
    }

    public NegotiationResponseDto(Integer negotiationId, ResumeEntity resume) {
        this.negotiationId = negotiationId;
        this.resume = resume;
    }

    public Integer getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Integer negotiationId) {
        this.negotiationId = negotiationId;
    }

    public ResumeEntity getResume() {
        return resume;
    }

    public void setResume(ResumeEntity resume) {
        this.resume = resume;
    }
}

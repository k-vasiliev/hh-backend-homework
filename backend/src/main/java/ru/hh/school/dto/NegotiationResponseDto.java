package ru.hh.school.dto;


import ru.hh.school.entity.ResumeEntity;

public class NegotiationResponseDto {
    private Long negotiationId;
    private ResumeEntity resume;

    public NegotiationResponseDto(Long negotiationId, ResumeEntity resume) {
        this.negotiationId = negotiationId;
        this.resume = resume;
    }

    public Long getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Long negotiationId) {
        this.negotiationId = negotiationId;
    }

    public ResumeEntity getResume() {
        return resume;
    }

    public void setResume(ResumeEntity resume) {
        this.resume = resume;
    }
}

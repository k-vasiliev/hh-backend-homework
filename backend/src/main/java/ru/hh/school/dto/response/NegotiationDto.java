package ru.hh.school.dto.response;

public class NegotiationDto {

    private Integer negotiationId;
    private ResumeDto resume;

    public Integer getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Integer negotiationId) {
        this.negotiationId = negotiationId;
    }

    public ResumeDto getResume() {
        return resume;
    }

    public void setResume(ResumeDto resume) {
        this.resume = resume;
    }
}

package ru.hh.school.dto.response;

public class NegotiationDto {

    private Integer negotiationId;
    private ResumeDto resume;
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

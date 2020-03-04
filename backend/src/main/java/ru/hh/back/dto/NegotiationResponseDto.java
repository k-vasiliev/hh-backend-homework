package ru.hh.back.dto;

public class NegotiationResponseDto {
    private Integer resumeId;
    private Integer vacancyId;
    private ResumeResponseDto resume;

    public ResumeResponseDto getResume() {
        return resume;
    }

    public void setResume(ResumeResponseDto resume) {
        this.resume = resume;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }
}

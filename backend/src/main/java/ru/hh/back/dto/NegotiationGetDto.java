package ru.hh.back.dto;

public class NegotiationGetDto {
    private Integer resumeId;
    private Integer vacancyId;
    private ResumeGetDto resume;

    public ResumeGetDto getResume() {
        return resume;
    }

    public void setResume(ResumeGetDto resume) {
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

package ru.hh.nab.dto;

public class ResponseNegotiationDTO {

    private int negotiationId;

    private int resumeId;

    private int vacancyId;

    private ResponseResumeDTO resume;

    public ResponseNegotiationDTO(int negotiationId, int resumeId, int vacancyId, ResponseResumeDTO resume) {
        this.negotiationId = negotiationId;
        this.resumeId = resumeId;
        this.vacancyId = vacancyId;
        this.resume = resume;
    }

    public int getNegotiationId() {
        return negotiationId;
    }

    public int getResumeId() {
        return resumeId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public ResponseResumeDTO getResume() {
        return resume;
    }

    public void setNegotiationId(int negotiationId) {
        this.negotiationId = negotiationId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public void setResume(ResponseResumeDTO resume) {
        this.resume = resume;
    }
}

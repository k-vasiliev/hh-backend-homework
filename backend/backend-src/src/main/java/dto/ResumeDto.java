package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResumeDto {
    @JsonProperty("title")
    private String resumeTitle;
    
    @JsonProperty("applicant")
    private ApplicantDto applicant;

    public ResumeDto(String resumeTitle, String applicantName, String dateCreate) {
        this.resumeTitle = resumeTitle;
        this.applicant = new ApplicantDto(applicantName);
        this.dateCreate = dateCreate;
    }

    public String getResumeTitle() {
        return resumeTitle;
    }

    public void setResumeTitle(String resumeTitle) {
        this.resumeTitle = resumeTitle;
    }

    public ApplicantDto getApplicant() {
        return applicant;
    }

    public void setApplicant(ApplicantDto applicant) {
        this.applicant = applicant;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    @JsonProperty("dateCreate")
    private String dateCreate;
}

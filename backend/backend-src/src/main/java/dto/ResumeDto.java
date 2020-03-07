package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.ResumeEntity;

public class ResumeDto {
    @JsonProperty("title")
    private String resumeTitle;

    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("applicant")
    private ApplicantDto applicant;

    @JsonProperty("dateCreate")
    private String dateCreate;

    public ResumeDto() { }

    public ResumeDto(ResumeEntity resume) {
        this.id = resume.getId();
        this.resumeTitle = resume.getTitle();
        this.applicant = new ApplicantDto(resume.getUser().getName());
        if (resume.getCreated() != null)
            this.dateCreate = resume.getCreated().toString();
        else
            this.dateCreate = "Incorrect DB TIMESTAMP handling";
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

}

package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.VacancyResponseEntity;

public class VacancyResponseDto {
    class ShortResumeInfo {
        @JsonProperty("id")
        Integer id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("applicant")
        private ApplicantDto applicant;

        public ShortResumeInfo(Integer id, String resumeTitle, String applicantName) {
            this.id = id;
            title = resumeTitle;
            applicant = new ApplicantDto(applicantName);
        }
    };

    @JsonProperty("resume")
    ShortResumeInfo info;

    public VacancyResponseDto(VacancyResponseEntity vacancyResponse) {
        info = new ShortResumeInfo(
                vacancyResponse.getResume().getId(),
                vacancyResponse.getResume().getTitle(),
                vacancyResponse.getResume().getUser().getName()
        );
    }
}

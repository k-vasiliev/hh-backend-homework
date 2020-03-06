package ru.hh.school.dto.response;

public class NegotiationResponseDto {

    private Integer id;
    private ResumeResponseDto resume;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ResumeResponseDto getResume() {
        return resume;
    }

    public void setResume(ResumeResponseDto resume) {
        this.resume = resume;
    }
}

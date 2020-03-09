package ru.hh.school.dto.response;

public class NegotiationResponseDto {

    private Integer id;
    private ResumeResponseDto resume;

    public NegotiationResponseDto(Integer id, ResumeResponseDto resume) {
        this.id = id;
        this.resume = resume;
    }

    public Integer getId() {
        return id;
    }

    public ResumeResponseDto getResume() {
        return resume;
    }

}

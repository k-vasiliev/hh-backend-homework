package ru.hh.backend.homework.dto.response;

import ru.hh.backend.homework.entity.ResumeEntity;

import java.util.Optional;

public class NegotiationResponseDto {

    private Long id;

    private ResumeEntity resume;

    public NegotiationResponseDto(Long id, ResumeEntity resume) {
        this.id = id;
        this.resume = resume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResumeEntity getResume() {
        return resume;
    }

    public void setResume(ResumeEntity resume) {
        this.resume = resume;
    }
}

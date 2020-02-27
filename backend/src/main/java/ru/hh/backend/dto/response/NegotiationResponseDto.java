package ru.hh.backend.dto.response;

import ru.hh.backend.entity.Resume;

public class NegotiationResponseDto {

    public NegotiationResponseDto(Long id, Resume resume) {
        this.id = id;
        this.resume = resume;
    }

    private Long id;

    private Resume resume;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}

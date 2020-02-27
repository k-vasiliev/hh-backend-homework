package ru.hh.backend.homework.dto.response;

import ru.hh.backend.homework.entity.ResumeEntity;

public class NegotiationResponseDto {
    private Integer negotiationId;
    private ResumeEntity resume;

    public NegotiationResponseDto(Integer negotiationId, ResumeEntity resume) {
        this.negotiationId = negotiationId;
        this.resume = resume;
    }
}

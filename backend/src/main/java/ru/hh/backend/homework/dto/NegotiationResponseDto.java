package ru.hh.backend.homework.dto;

import ru.hh.backend.homework.entity.ResumeEntity;

import java.util.Optional;

public class NegotiationResponseDto {
    private Integer negotiationId;
    private Optional<ResumeEntity> resume;

    public NegotiationResponseDto(Integer negotiationId, Optional<ResumeEntity> resume) {
        this.negotiationId = negotiationId;
        this.resume = resume;
    }
}

package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.NegotiationRequestDto;
import ru.hh.backend.homework.dto.NegotiationResponseDto;
import ru.hh.backend.homework.entity.NegotiationEntity;
import ru.hh.backend.homework.service.ResumeService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NegotiationMapper {
    private final ResumeService resumeService;

    @Inject
    public NegotiationMapper(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    public NegotiationEntity map(NegotiationRequestDto negotiationRequestDto) {
        NegotiationEntity negotiation = new NegotiationEntity();
        negotiation.setResumeEntity(negotiationRequestDto.getResumeId());
        negotiation.setVacancyEntity(negotiationRequestDto.getVacancyId());
        return negotiation;
    }

    public NegotiationResponseDto map(NegotiationEntity negotiationEntity) {
        return new NegotiationResponseDto(negotiationEntity.getNegotiationId(),
                resumeService.get(negotiationEntity.getResumeEntity()));
    }
}

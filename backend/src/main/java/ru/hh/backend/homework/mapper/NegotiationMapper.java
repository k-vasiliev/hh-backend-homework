package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.NegotiationRequestDto;
import ru.hh.backend.homework.dto.NegotiationResponseDto;
import ru.hh.backend.homework.entity.NegotiationEntity;
import ru.hh.backend.homework.service.ResumeService;
import ru.hh.backend.homework.service.VacancyService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NegotiationMapper {
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @Inject
    public NegotiationMapper(ResumeService resumeService, VacancyService vacancyService) {
        this.resumeService = resumeService;
        this.vacancyService = vacancyService;
    }

    public NegotiationEntity map(NegotiationRequestDto negotiationRequestDto) {
        NegotiationEntity negotiation = new NegotiationEntity();
        negotiation.setResumeEntity(resumeService.get(negotiationRequestDto.getResumeId()));
        negotiation.setVacancyEntity(vacancyService.get(negotiationRequestDto.getVacancyId()));
        return negotiation;
    }

    public NegotiationResponseDto map(NegotiationEntity negotiationEntity) {
        return new NegotiationResponseDto(negotiationEntity.getNegotiationId(),
                negotiationEntity.getResumeEntity());
    }
}

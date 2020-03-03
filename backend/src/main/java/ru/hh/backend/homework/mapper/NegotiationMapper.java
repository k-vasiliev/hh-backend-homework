package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.NegotiationRequestDto;
import ru.hh.backend.homework.dto.NegotiationResponseDto;
import ru.hh.backend.homework.dto.ResumeResponseDto;
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
        return new NegotiationEntity(resumeService.get(negotiationRequestDto.getResumeId()),
                vacancyService.get(negotiationRequestDto.getVacancyId()));
    }

    public NegotiationResponseDto map(NegotiationEntity negotiationEntity) {
        return new NegotiationResponseDto(negotiationEntity.getNegotiationId(),
                new ResumeResponseDto(negotiationEntity.getResumeEntity().getTitle(),
                        negotiationEntity.getResumeEntity().getResumeId(),
                        negotiationEntity.getResumeEntity().getCreationDate()));
    }
}

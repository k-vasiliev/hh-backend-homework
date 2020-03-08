package ru.hh.school.mapper;



import ru.hh.school.dto.NegotiationRequestDto;
import ru.hh.school.dto.NegotiationResponseDto;
import ru.hh.school.service.ResumeService;
import ru.hh.school.entity.NegotiationEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NegotiationMapper {

    private final ResumeService resumeService;


    public NegotiationMapper(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    public NegotiationEntity map(NegotiationRequestDto negotiationRequestDto) {
        NegotiationEntity negotiation = new NegotiationEntity();
        negotiation.setResume(negotiationRequestDto.getResumeEntity());
        negotiation.setVacancy (negotiationRequestDto.getVacancyEntity());
        return negotiation;
    }

    public NegotiationResponseDto map(NegotiationEntity negotiationEntity) {
        return new NegotiationResponseDto(negotiationEntity.getId(),
                resumeService.get(negotiationEntity.getResume().getId()));
    }
}
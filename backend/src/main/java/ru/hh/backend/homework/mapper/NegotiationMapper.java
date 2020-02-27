package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.NegotiationRequestDto;
import ru.hh.backend.homework.dto.NegotiationResponseDto;
import ru.hh.backend.homework.entity.NegotiationEntity;
import ru.hh.backend.homework.entity.ResumeEntity;

import javax.inject.Singleton;

@Singleton
public class NegotiationMapper {
    public NegotiationEntity map(NegotiationRequestDto negotiationRequestDto) {
        NegotiationEntity negotiation = new NegotiationEntity();
        negotiation.setResumeEntity(negotiationRequestDto.getResumeId());
        negotiation.setVacancyEntity(negotiationRequestDto.getVacancyId());
        return negotiation;
    }

    public NegotiationResponseDto map(NegotiationEntity negotiationEntity) {
        //добавить нормальную ResumeEntity
        return new NegotiationResponseDto(negotiationEntity.getNegotiationId(), new ResumeEntity());
    }
}

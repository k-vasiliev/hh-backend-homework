package ru.hh.backend.homework.mapper;

import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dto.NegotiationDto;
import ru.hh.backend.homework.entity.NegotiationEntity;

@Service
public class NegotiationMapper {
    public NegotiationDto map(NegotiationEntity negotiationEntity) {
        return new NegotiationDto(negotiationEntity.getId(),
                negotiationEntity.getCreationDate(),
                negotiationEntity.getModificationDate(),
                negotiationEntity.getResumeEntity(),
                negotiationEntity.getVacancyEntity());
    }
}

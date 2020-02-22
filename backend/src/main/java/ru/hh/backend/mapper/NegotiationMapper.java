package ru.hh.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hh.backend.dao.ResumeDao;
import ru.hh.backend.dao.VacancyDao;
import ru.hh.backend.dto.request.NegotiationRequestDto;
import ru.hh.backend.dto.response.NegotiationResponseDto;
import ru.hh.backend.model.Negotiation;


@Mapper(componentModel = "spring", uses = {ResumeDao.class, VacancyDao.class})
public interface NegotiationMapper {

    @Mapping(source = "resumeId", target = "resume")
    @Mapping(source = "vacancyId", target = "vacancy")
    Negotiation map(NegotiationRequestDto negotiationRequestDto);

    NegotiationResponseDto map(Negotiation negotiation);
}

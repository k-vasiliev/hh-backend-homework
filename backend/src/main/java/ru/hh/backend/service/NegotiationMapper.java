package ru.hh.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hh.backend.dao.ResumeDao;
import ru.hh.backend.dao.VacancyDao;
import ru.hh.backend.dto.request.NegotiationRequestDto;
import ru.hh.backend.dto.response.NegotiationResponseDto;
import ru.hh.backend.entity.Negotiation;

@Service
public class NegotiationMapper {

    ResumeDao resumeDao;
    VacancyDao vacancyDao;

    @Autowired
    public NegotiationMapper(ResumeDao resumeDao, VacancyDao vacancyDao) {
        this.resumeDao = resumeDao;
        this.vacancyDao = vacancyDao;
    }

    public Negotiation map(NegotiationRequestDto negotiationRequestDto) {
        Negotiation negotiation = new Negotiation();
        negotiation.setResume(resumeDao.get(negotiationRequestDto.getResumeId()));
        negotiation.setVacancy(vacancyDao.get(negotiationRequestDto.getVacancyId()));
        return negotiation;
    }

    public NegotiationResponseDto map(Negotiation negotiation) {
        return new NegotiationResponseDto(negotiation.getId(), negotiation.getResume());
    }
}

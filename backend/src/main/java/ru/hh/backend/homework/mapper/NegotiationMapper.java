package ru.hh.backend.homework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dao.ResumeDao;
import ru.hh.backend.homework.dao.VacancyDao;
import ru.hh.backend.homework.dto.request.NegotiationRequestDto;
import ru.hh.backend.homework.dto.response.NegotiationResponseDto;
import ru.hh.backend.homework.entity.NegotiationEntity;
import ru.hh.backend.homework.service.ResumeService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service
public class NegotiationMapper {

    ResumeDao resumeDao;
    VacancyDao vacancyDao;

    @Autowired
    public NegotiationMapper(ResumeDao resumeDao, VacancyDao vacancyDao) {
        this.resumeDao = resumeDao;
        this.vacancyDao = vacancyDao;
    }

    public NegotiationEntity map(NegotiationRequestDto negotiationRequestDto) {
        NegotiationEntity negotiation = new NegotiationEntity();
        negotiation.setResume(resumeDao.get(negotiationRequestDto.getResumeId()));
        negotiation.setVacancy(vacancyDao.get(negotiationRequestDto.getVacancyId()));
        return negotiation;
    }

    public NegotiationResponseDto map(NegotiationEntity negotiation) {
        return new NegotiationResponseDto(negotiation.getId(), negotiation.getResume());
    }
}

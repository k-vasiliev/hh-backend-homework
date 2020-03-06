package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.*;
import ru.hh.school.dto.request.NegotiationRequestDto;
import ru.hh.school.dto.response.NegotiationResponseDto;
import ru.hh.school.entity.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class NegotiationService {

    private NegotiationDao negotiationDao;
    private ResumeDao resumeDao;
    private VacancyDao vacancyDao;

    @Inject
    public NegotiationService(NegotiationDao negotiationDao, ResumeDao resumeDao, VacancyDao vacancyDao) {
        this.negotiationDao = negotiationDao;
        this.resumeDao = resumeDao;
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public void saveNew(NegotiationRequestDto negotiationDto) {
        Resume resume = resumeDao.get(negotiationDto.getResumeId());
        Vacancy vacancy = vacancyDao.get(negotiationDto.getVacancyId());
        Negotiation negotiation = new Negotiation();
        negotiation.setResume(resume);
        negotiation.setVacancy(vacancy);
        negotiationDao.create(negotiation);
    }

    @Transactional
    public List<NegotiationResponseDto> getNegotiationsByVacancyId(Integer vacancyId) {
        return negotiationDao.getByVacancyId(vacancyId).stream()
                .map(NegotiationService::mapped)
                .collect(Collectors.toList());
    }

    private static NegotiationResponseDto mapped(Negotiation negotiation) {
        NegotiationResponseDto negotiationDto = new NegotiationResponseDto();
        negotiationDto.setId(negotiation.getId());
        negotiationDto.setResume(ResumeService.mapped(negotiation.getResume()));
        return negotiationDto;
    }
}

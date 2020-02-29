package ru.hh.school.service;

import ru.hh.school.dao.NegotiationDao;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.request.CreateNegotiationDto;
import ru.hh.school.dto.response.NegotiationDto;
import ru.hh.school.entity.Negotiation;
import ru.hh.school.entity.Resume;
import ru.hh.school.entity.Vacancy;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class NegotiationService {

    private NegotiationDao negotiationDao;
    private ResumeDao resumeDao;
    private VacancyDao vacancyDao;

    @Inject
    public NegotiationService(NegotiationDao negotiationDao,ResumeDao resumeDao, VacancyDao vacancyDao) {
        this.negotiationDao = negotiationDao;
        this.resumeDao = resumeDao;
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public void createNegotiation(CreateNegotiationDto dto) {
        Vacancy vacancy = vacancyDao.getVacancy(dto.getVacancyId());
        Resume resume = resumeDao.getResume(dto.getResumeId());
        if (vacancy == null || resume == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationId(dto.getNegotiationId());
        negotiation.setResume(resume);
        negotiation.setVacancy(vacancy);
        negotiation.setCreatedAt(Instant.now());
        negotiation.setUpdateAt(Instant.now());
        negotiationDao.save(negotiation);
    }

    @Transactional
    public List<NegotiationDto> getNegotiationsByVacancyId(Integer vacancyId) {
        return negotiationDao.getNegotiationsByVacancyId(vacancyId).stream()
            .map(NegotiationService::convert).collect(Collectors.toList());
    }

    private static NegotiationDto convert(Negotiation negotiation) {
        NegotiationDto negotiationDto = new NegotiationDto();
        negotiationDto.setNegotiationId(negotiation.getNegotiationId());
        negotiationDto.setResume(ResumeService.convert(negotiation.getResume()));
        return negotiationDto;
    }

}

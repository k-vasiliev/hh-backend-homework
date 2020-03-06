package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.NegotiationDao;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.NegotiationRequestDto;
import ru.hh.school.entity.Negotiation;
import ru.hh.school.entity.Resume;
import ru.hh.school.entity.Vacancy;

import javax.inject.Inject;
import javax.inject.Singleton;

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
        if (vacancy == null || resume == null) {

        }
        Negotiation negotiation = new Negotiation();
        negotiation.setResume(resume);
        negotiation.setVacancy(vacancy);
        negotiationDao.create(negotiation);
    }
}

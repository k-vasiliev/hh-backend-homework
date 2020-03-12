package ru.hh.homework.at_least_some_backend.service;

import ru.hh.homework.at_least_some_backend.dao.HHNegotiationDao;
import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertNegotiationDto;
import ru.hh.homework.at_least_some_backend.entity.HHNegotiation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.List;

@Singleton
public class HHNegotiationService extends HHEntityService<HHNegotiation, HHInsertNegotiationDto>
{
    @Inject
    private HHNegotiationDao dao;
    @Inject
    private HHVacancyService vacancyService;
    @Inject
    private HHResumeService resumeService;

    @Override
    protected HHNegotiationDao getDao() { return dao; }
    protected HHVacancyService getVacancyService() { return vacancyService; }
    protected HHResumeService getResumeService() { return resumeService; }

    @Override
    @Transactional
    public HHNegotiation createEntity(HHInsertNegotiationDto dto)
    {
        if (dto == null) return null;

        var vacancyId = dto.getVacancyId();
        if (vacancyId == null) throw new BadRequestException("'vacancyId' must be not null.");

        var resumeId = dto.getResumeId();
        if (resumeId == null) throw new BadRequestException("'resumeId' must be not null.");

        var vacancy = getVacancyService().queryById(vacancyId);
        if (vacancy == null) throw new BadRequestException("Couldn't find vacancy with the specified 'vacancyId'.");

        var resume = getResumeService().queryById(resumeId);
        if (resume == null) throw new BadRequestException("Couldn't find resume with the specified 'resumeId'.");

        var entity = new HHNegotiation();

        entity.setVacancy(vacancy);
        entity.setResume(resume);

        return entity;
    }

    @Transactional
    public List<HHNegotiation> queryAllByVacancyId(Long vacancyId) { return getDao().queryAllByVacancyId(vacancyId); }
}

package ru.hh.homework.at_least_some_backend.service;

import ru.hh.homework.at_least_some_backend.dao.HHVacancyDao;
import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertVacancyDto;
import ru.hh.homework.at_least_some_backend.entity.HHVacancy;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;

@Singleton
public class HHVacancyService extends HHEntityService<HHVacancy, HHInsertVacancyDto>
{
    @Inject
    private HHVacancyDao dao;
    @Inject
    private HHCompanyService companyService;

    @Override
    protected HHVacancyDao getDao() { return dao; }

    protected HHCompanyService getCompanyService() { return companyService; }

    @Override
    @Transactional
    public HHVacancy createEntity(HHInsertVacancyDto dto)
    {
        if (dto == null) return null;

        var title = dto.getTitle();
        if (title == null) throw new BadRequestException("'title' must be not null.");

        var salary = dto.getSalary();

        var description = dto.getDescription();
        if (description == null) throw new BadRequestException("'description' must be not null.");

        var contacts = dto.getContacts();
        if (contacts == null) throw new BadRequestException("'contacts' must be not null.");

        var companyId = dto.getCompanyId();
        if (companyId == null) throw new BadRequestException("'companyId' must be not null.");

        var company = getCompanyService().queryById(companyId);
        if (company == null) throw new BadRequestException("Couldn't find user with the specified 'userId'.");

        var entity = new HHVacancy();

        entity.setTitle(title);
        entity.setSalary(salary);
        entity.setDescription(description);
        entity.setContacts(contacts);
        entity.setCompany(company);

        return entity;
    }
}

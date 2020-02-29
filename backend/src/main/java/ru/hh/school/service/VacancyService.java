package ru.hh.school.service;

import ru.hh.school.Utils;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.request.CreateVacancyDto;
import ru.hh.school.dto.response.ShortVacancyDto;
import ru.hh.school.dto.response.VacancyDto;
import ru.hh.school.entity.Company;
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
public class VacancyService {

    private VacancyDao vacancyDao;
    private CompanyDao companyDao;

    @Inject
    public VacancyService(VacancyDao vacancyDao, CompanyDao companyDao) {
        this.vacancyDao = vacancyDao;
        this.companyDao = companyDao;
    }

    @Transactional
    public void createVacancy(CreateVacancyDto dto) {
        Company company = companyDao.getCompany(dto.getCompanyId());
        if (company == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle(dto.getTitle());
        vacancy.setSalary(dto.getSalary());
        vacancy.setContacts(dto.getContacts());
        vacancy.setDescription(dto.getDescription());
        vacancy.setCreatedAt(Instant.now());
        vacancy.setUpdateAt(Instant.now());
        vacancy.setCompany(company);
        vacancyDao.save(vacancy);
    }

    @Transactional
    public List<ShortVacancyDto> getAllVacancies() {
        return vacancyDao.getAllVacancies().stream()
            .map(VacancyService::convertToShortVacancy).collect(Collectors.toList());
    }

    @Transactional
    public VacancyDto getVacancy(Integer vacancyId) {
        Vacancy vacancy = vacancyDao.getVacancy(vacancyId);
        if (vacancy == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return convertToVacancy(vacancy);
    }

    private static void updateWithBasicFields(ShortVacancyDto vacancyDto, Vacancy vacancy) {
        vacancyDto.setId(vacancy.getVacancyId());
        vacancyDto.setTitle(vacancy.getTitle());
        vacancyDto.setDateCreate(Utils.dateTimeFormatter.format(vacancy.getCreatedAt()));
        vacancyDto.setCompany(CompanyService.convert(vacancy.getCompany()));
    }

    private static ShortVacancyDto convertToShortVacancy(Vacancy vacancy) {
        ShortVacancyDto vacancyDto = new ShortVacancyDto();
        updateWithBasicFields(vacancyDto, vacancy);
        return vacancyDto;
    }

    private static VacancyDto convertToVacancy(Vacancy vacancy) {
        VacancyDto vacancyDto = new VacancyDto();
        updateWithBasicFields(vacancyDto, vacancy);
        vacancyDto.setSalary(vacancy.getSalary());
        vacancyDto.setDescription(vacancy.getDescription());
        vacancyDto.setContacts(vacancy.getContacts());
        return vacancyDto;
    }

}

package ru.hh.school.service;

import org.springframework.stereotype.Service;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.request.CreateVacancyDto;
import ru.hh.school.dto.response.VacancyDto;
import ru.hh.school.entity.Company;
import ru.hh.school.entity.Vacancy;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    private VacancyDao vacancyDao;
    private CompanyDao companyDao;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.MEDIUM)
        .withLocale(Locale.forLanguageTag("RU"))
        .withZone(ZoneId.of("Europe/Moscow"));

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
    public List<VacancyDto> getAllVacancies() {
        return vacancyDao.getAllVacancies().stream()
            .map(VacancyService::convert).collect(Collectors.toList());
    }

    private static VacancyDto convert(Vacancy vacancy) {
        VacancyDto vacancyDto = new VacancyDto();
        vacancyDto.setId(vacancy.getVacancyId());
        vacancyDto.setTitle(vacancy.getTitle());
        vacancyDto.setDateCreate(dateTimeFormatter.format(vacancy.getCreatedAt()));
        vacancyDto.setCompany(CompanyService.convert(vacancy.getCompany()));
        return vacancyDto;
    }

}

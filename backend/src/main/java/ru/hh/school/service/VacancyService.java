package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.response.VacancyPopupResponseDto;
import ru.hh.school.dto.request.VacancyRequestDto;
import ru.hh.school.dto.response.VacancyResponseDto;
import ru.hh.school.entity.Company;
import ru.hh.school.entity.Vacancy;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
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
    public void saveNew(VacancyRequestDto vacancyDto) {
        Company company = companyDao.get(vacancyDto.getCompanyId())
                .orElseThrow(NotFoundException::new);
        vacancyDao.create(mapToEntity(vacancyDto, company));
    }

    @Transactional
    public List<VacancyResponseDto> getAll() {
        return vacancyDao.getAll().stream()
                .map(VacancyService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public VacancyPopupResponseDto getVacancyDtoById(Integer vacancyId) {
        return mapPopupToDto(vacancyDao.get(vacancyId).orElseThrow(NotFoundException::new));
    }

    private Vacancy mapToEntity(VacancyRequestDto vacancyDto, Company company) {
        return new Vacancy(
                company,
                vacancyDto.getTitle(),
                vacancyDto.getSalary(),
                vacancyDto.getDescription(),
                vacancyDto.getContacts());
    }

    private static VacancyResponseDto mapToDto(Vacancy vacancy) {
        return new VacancyResponseDto(
                vacancy.getId(),
                vacancy.getTitle(),
                vacancy.getCreationDate().toString(),
                CompanyService.mapToDto(vacancy.getCompany()));
    }

    private static VacancyPopupResponseDto mapPopupToDto(Vacancy vacancy) {
        return new VacancyPopupResponseDto(
                vacancy.getId(),
                vacancy.getTitle(),
                vacancy.getCreationDate().toString(),
                CompanyService.mapToDto(vacancy.getCompany()),
                vacancy.getCompensation(),
                vacancy.getDescription(),
                vacancy.getContacts());
    }
}

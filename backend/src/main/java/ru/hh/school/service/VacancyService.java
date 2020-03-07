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
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle(vacancyDto.getTitle());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setContacts(vacancyDto.getContacts());
        vacancy.setCompensation(vacancyDto.getSalary());
        vacancy.setCompany(company);
        return vacancy;
    }

    private static VacancyResponseDto mapToDto(Vacancy vacancy) {
        VacancyResponseDto vacancyDto = new VacancyResponseDto();
        mapCommonFieldsToDto(vacancy, vacancyDto);
        vacancyDto.setTitle(vacancy.getTitle());
        vacancyDto.setDateCreate(vacancy.getCreationDate().toString());
        return vacancyDto;
    }

    private static VacancyPopupResponseDto mapPopupToDto(Vacancy vacancy) {
        VacancyPopupResponseDto vacancyDto = new VacancyPopupResponseDto();
        mapCommonFieldsToDto(vacancy, vacancyDto);
        vacancyDto.setSalary(vacancy.getCompensation());
        vacancyDto.setDescription(vacancy.getDescription());
        vacancyDto.setContacts(vacancy.getContacts());
        return vacancyDto;
    }

    private static void mapCommonFieldsToDto(Vacancy vacancy, VacancyResponseDto vacancyDto) {
        vacancyDto.setId(vacancy.getId());
        vacancyDto.setCompany(CompanyService.mapToDto(vacancy.getCompany()));
    }
}

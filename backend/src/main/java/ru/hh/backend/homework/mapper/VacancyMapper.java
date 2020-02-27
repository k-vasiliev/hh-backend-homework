package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.VacancyRequestDto;
import ru.hh.backend.homework.dto.VacancyResponseDto;
import ru.hh.backend.homework.entity.CompanyEntity;
import ru.hh.backend.homework.entity.VacancyEntity;
import ru.hh.backend.homework.service.CompanyService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VacancyMapper {
    private final CompanyService companyService;

    @Inject
    public VacancyMapper(CompanyService companyService) {
        this.companyService = companyService;
    }

    public VacancyEntity map(VacancyRequestDto vacancyRequestDto) {
        VacancyEntity vacancy = new VacancyEntity();
        vacancy.setTitle(vacancyRequestDto.getTitle());
        vacancy.setCompanyEntity(vacancyRequestDto.getCompanyId());
        vacancy.setSalary(vacancyRequestDto.getSalary());
        vacancy.setDescription(vacancyRequestDto.getDescription());
        vacancy.setContacts(vacancyRequestDto.getContacts());
        return vacancy;
    }

    public VacancyResponseDto map(VacancyEntity vacancyEntity) {
        return new VacancyResponseDto(vacancyEntity.getVacancyId(), vacancyEntity.getTitle(),
                vacancyEntity.getCreationDate(), companyService.get(vacancyEntity.getCompanyEntity()));
    }
}

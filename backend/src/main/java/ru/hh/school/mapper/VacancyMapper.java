package ru.hh.school.mapper;


import ru.hh.school.dto.VacancyDetailsResponseDto;
import ru.hh.school.dto.VacancyRequestDto;
import ru.hh.school.dto.VacancyResponseDto;
import ru.hh.school.entity.VacancyEntity;
import ru.hh.school.service.CompanyService;

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
        vacancy.setHeader(vacancyRequestDto.getTitle());
        vacancy.setCompanyEntity(vacancyRequestDto.getCompanyEntity());
        vacancy.setSalary(vacancyRequestDto.getSalary());
        vacancy.setDescription(vacancyRequestDto.getDescription());
        vacancy.setContacts(vacancyRequestDto.getContacts());
        return vacancy;
    }

    public VacancyResponseDto map(VacancyEntity vacancyEntity) {
        return new VacancyResponseDto(vacancyEntity.getId(), vacancyEntity.getHeader(),
                vacancyEntity.getCreation_time(), vacancyEntity.getCompanyEntity()); //companyService.get(vacancyEntity.getCompanyEntity())
    }

    public VacancyDetailsResponseDto mapDetails(VacancyEntity vacancyEntity) {
        return new VacancyDetailsResponseDto(vacancyEntity.getId(),
                vacancyEntity.getHeader(),
                vacancyEntity.getSalary(),
                vacancyEntity.getDescription(),
                vacancyEntity.getContacts(),
                vacancyEntity.getCompanyEntity());
    }
}
package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.CompanyResponseDto;
import ru.hh.backend.homework.dto.VacancyDetailsResponseDto;
import ru.hh.backend.homework.dto.VacancyRequestDto;
import ru.hh.backend.homework.dto.VacancyResponseDto;
import ru.hh.backend.homework.entity.CompanyEntity;
import ru.hh.backend.homework.entity.VacancyEntity;
import ru.hh.backend.homework.service.CompanyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class VacancyMapper {
    private final CompanyService companyService;

    @Inject
    public VacancyMapper(CompanyService companyService) {
        this.companyService = companyService;
    }

    public VacancyEntity map(VacancyRequestDto vacancyRequestDto) {
        return new VacancyEntity(vacancyRequestDto.getTitle(),
                vacancyRequestDto.getSalary(),
                vacancyRequestDto.getDescription(),
                vacancyRequestDto.getContacts(),
                companyService.get(vacancyRequestDto.getCompanyId()));
    }

    public VacancyResponseDto map(VacancyEntity vacancyEntity) {
        return new VacancyResponseDto(vacancyEntity.getVacancyId(),
                vacancyEntity.getTitle(),
                vacancyEntity.getCreationDate(),
                new CompanyResponseDto(vacancyEntity.getCompanyEntity().getCompanyId(),
                        vacancyEntity.getCompanyEntity().getName()));
    }

    public VacancyDetailsResponseDto mapDetails(VacancyEntity vacancyEntity) {
        return new VacancyDetailsResponseDto(vacancyEntity.getVacancyId(),
                vacancyEntity.getTitle(),
                vacancyEntity.getSalary(),
                vacancyEntity.getDescription(),
                vacancyEntity.getContacts(),
                new CompanyResponseDto(vacancyEntity.getCompanyEntity().getCompanyId(),
                        vacancyEntity.getCompanyEntity().getName()));
    }
}

package ru.hh.backend.mapper;

import javassist.NotFoundException;
import ru.hh.backend.dto.VacancyDtoRequest;
import ru.hh.backend.dto.VacancyDtoResponse;
import ru.hh.backend.entity.Vacancy;
import ru.hh.backend.service.CompanyService;

import javax.inject.Singleton;
import java.time.LocalDate;

@Singleton
public class VacancyMapper {

    private CompanyService companyService;
    private CompanyMapper companyMapper;

    public VacancyMapper(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    public VacancyDtoResponse map(Vacancy vacancy) {
        return new VacancyDtoResponse(
                vacancy.getVacancyId(),
                vacancy.getTitle(),
                vacancy.getSalary(),
                vacancy.getDescription(),
                vacancy.getContacts(),
                vacancy.getCreationDate().toString(),
                companyMapper.map(vacancy.getCompany()));
    }

    public Vacancy map(VacancyDtoRequest vacancyDtoRequest) throws NotFoundException {
        return new Vacancy(
                vacancyDtoRequest.getTitle(),
                companyService.getCompany(vacancyDtoRequest.getCompanyId()),
                vacancyDtoRequest.getSalary(),
                vacancyDtoRequest.getDescription(),
                vacancyDtoRequest.getContacts(),
                LocalDate.now());
    }
}

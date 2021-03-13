package ru.hh.school.mapper;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dto.area.AreaDto;
import ru.hh.school.dto.company.CompanyDtoRequest;
import ru.hh.school.dto.salary.SalaryDto;
import ru.hh.school.dto.vacancy.VacancyDtoRequest;
import ru.hh.school.dto.vacancy.VacancyDtoResponse;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Company;
import ru.hh.school.entity.Salary;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.service.AreaService;
import ru.hh.school.service.CompanyService;
import ru.hh.school.service.SalaryService;

import javax.inject.Singleton;
import java.time.LocalDate;

@Singleton
public class VacancyMapper {

    private final AreaService areaService;
    private final AreaMapper areaMapper;
    private final SalaryService salaryService;
    private final SalaryMapper salaryMapper;
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public VacancyMapper(AreaService areaService, AreaMapper areaMapper, SalaryService salaryService, SalaryMapper salaryMapper, CompanyService companyService, CompanyMapper companyMapper) {
        this.areaService = areaService;
        this.areaMapper = areaMapper;
        this.salaryService = salaryService;
        this.salaryMapper = salaryMapper;
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public VacancyDtoRequest mapWithSideEffects(Vacancy vacancy) {
        AreaDto area = areaMapper.map(vacancy.getArea());
        SalaryDto salary = salaryMapper.map(vacancy.getSalary());
        CompanyDtoRequest companyDtoRequest = companyMapper.mapWithSideEffects(vacancy.getEmployer());
        VacancyDtoRequest.Popularity popularity = (vacancy.getViewsCount() > 50) ? VacancyDtoRequest.Popularity.POPULAR : VacancyDtoRequest.Popularity.REGULAR;
        return new VacancyDtoRequest(
                vacancy.getVacancyId(),
                vacancy.getName(),
                vacancy.getCreationDate(),
                area,
                salary,
                vacancy.getCreatedAt(),
                companyDtoRequest,
                popularity,
                vacancy.getViewsCount() + 1,
                vacancy.getComment()
        );
    }

    @Transactional
    public Vacancy mapWithSideEffects(VacancyDtoResponse vacancyDtoResponse, String comment) throws NotFoundException {
        Area area = areaMapper.map(vacancyDtoResponse.getArea());
        areaService.saveOrUpdate(area);
        Salary salary = salaryMapper.map(vacancyDtoResponse.getSalary());
        salaryService.saveOrUpdate(salary);
        Company company = companyService.getCompany(vacancyDtoResponse.getEmployer().getId());
        return new Vacancy(
                vacancyDtoResponse.getId(),
                vacancyDtoResponse.getName(),
                area,
                salary,
                vacancyDtoResponse.getCreated_at(),
                company,
                comment,
                0,
                LocalDate.now());
    }

    @Transactional
    public Vacancy convertWithSideEffects(VacancyDtoResponse vacancyDtoResponse, Vacancy vacancy) {
        Area area = areaMapper.map(vacancyDtoResponse.getArea());
        areaService.saveOrUpdate(area);
        Salary salary = salaryMapper.map(vacancyDtoResponse.getSalary());
        salaryService.saveOrUpdate(salary);
        return new Vacancy(
                vacancy.getId(),
                vacancyDtoResponse.getId(),
                vacancyDtoResponse.getName(),
                vacancy.getCreationDate(),
                area,
                salary,
                vacancyDtoResponse.getCreated_at(),
                vacancy.getEmployer(),
                vacancy.getComment(),
                vacancy.getViewsCount());
    }
}

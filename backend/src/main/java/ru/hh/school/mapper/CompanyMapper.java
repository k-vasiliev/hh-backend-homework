package ru.hh.school.mapper;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dto.area.AreaDto;
import ru.hh.school.dto.company.CompanyDtoByIdResponse;
import ru.hh.school.dto.company.CompanyDtoRequest;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Company;
import ru.hh.school.service.AreaService;

import javax.inject.Singleton;
import java.time.LocalDate;


@Singleton
public class CompanyMapper {

    private final AreaService areaService;
    private final AreaMapper areaMapper;

    public CompanyMapper(AreaService areaService, AreaMapper areaMapper) {
        this.areaService = areaService;
        this.areaMapper = areaMapper;
    }

    @Transactional
    public CompanyDtoRequest mapWithSideEffects(Company company) {
        AreaDto area = areaMapper.map(company.getArea());
        CompanyDtoRequest.Popularity popularity = (company.getViewsCount() > 50) ? CompanyDtoRequest.Popularity.POPULAR : CompanyDtoRequest.Popularity.REGULAR;
        return new CompanyDtoRequest(
                company.getCompanyId(),
                company.getName(),
                company.getCreationDate(),
                company.getDescription(),
                area,
                company.getComment(),
                popularity,
                company.getViewsCount() + 1
        );
    }

    @Transactional
    public Company mapWithSideEffects(CompanyDtoByIdResponse companyDtoRequest, String comment) {
        Area area = areaMapper.map(companyDtoRequest.getArea());
        areaService.saveOrUpdate(area);
        return new Company(
                companyDtoRequest.getId(),
                companyDtoRequest.getName(),
                companyDtoRequest.getDescription(),
                area,
                comment,
                0,
                LocalDate.now());
    }

    @Transactional
    public Company convertWithSideEffects(CompanyDtoByIdResponse companyDtoRequest, Company company) {
        Area area = areaMapper.map(companyDtoRequest.getArea());
        areaService.saveOrUpdate(area);
        return new Company(
                company.getId(),
                companyDtoRequest.getId(),
                companyDtoRequest.getName(),
                companyDtoRequest.getDescription(),
                area,
                company.getComment(),
                company.getViewsCount(),
                company.getCreationDate());
    }
}

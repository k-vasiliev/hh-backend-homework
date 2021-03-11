package ru.hh.school.mapper;

import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;

@Singleton
public class VacancyMapper {

    private final AreaMapper areaMapper;

    private final EmployerMapper employerMapper;

    public VacancyMapper(AreaMapper areaMapper, EmployerMapper employerMapper) {
        this.areaMapper = areaMapper;
        this.employerMapper = employerMapper;
    }

    public Vacancy map(VacancyDto vacancyDto) {
        return vacancyDto == null ? null : new Vacancy(
                vacancyDto.getId(),
                vacancyDto.getCreatedAt(),
                vacancyDto.getName(),
                areaMapper.map(vacancyDto.getArea()),
                employerMapper.map(vacancyDto.getEmployer()));
    }

}

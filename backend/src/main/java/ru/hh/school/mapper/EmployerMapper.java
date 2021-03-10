package ru.hh.school.mapper;

import ru.hh.school.dto.EmployerByIdDto;
import ru.hh.school.entity.Employer;

import javax.inject.Singleton;

@Singleton
public class EmployerMapper {

    private final AreaMapper areaMapper;

    public EmployerMapper(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    public Employer map(EmployerByIdDto employer) {
        return employer == null ? null : new Employer(
                employer.getId(),
                employer.getName(),
                employer.getDescription(),
                areaMapper.map(employer.getArea()));
    }
}

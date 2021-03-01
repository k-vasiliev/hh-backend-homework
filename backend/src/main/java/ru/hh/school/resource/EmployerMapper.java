package ru.hh.school.resource;

import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.entity.AreaEntity;
import ru.hh.school.entity.EmployerEntity;

public final class EmployerMapper {

    public static EmployerDto map(EmployerEntity employerEntity) {
        AreaEntity area = employerEntity.getArea();
        AreaDto areaDto = AreaMapper.map(area);

        return new EmployerDto(employerEntity.getId(),
                employerEntity.getName(),
                employerEntity.getDescription(),
                areaDto);
    }
}

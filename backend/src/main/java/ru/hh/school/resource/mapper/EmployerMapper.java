package ru.hh.school.resource.mapper;

import org.mapstruct.Mapper;
import ru.hh.school.resource.dto.EmployerResponseDto;
import ru.hh.school.resource.dto.EmployersResponseDto;
import ru.hh.school.resource.dto.HHEmployerResponseDto;
import ru.hh.school.resource.dto.HHEmployersResponseDto;

@Mapper(componentModel = "spring")
public interface EmployerMapper {
    EmployersResponseDto toEmployersDto(HHEmployersResponseDto hhEmployers);

    EmployerResponseDto toEmployerDto(HHEmployerResponseDto hhEmployer);
}
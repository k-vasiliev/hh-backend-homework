package ru.hh.school.resource.mapper;

import org.mapstruct.Mapper;
import ru.hh.school.resource.dto.HHVacanciesResponseDto;
import ru.hh.school.resource.dto.HHVacancyResponseDto;
import ru.hh.school.resource.dto.VacanciesResponseDto;
import ru.hh.school.resource.dto.VacancyResponseDto;

@Mapper(componentModel = "spring", uses = {AreaMapper.class})
public interface VacancyMapper {
    VacanciesResponseDto toVacanciesDto(HHVacanciesResponseDto hhVacancies);

    VacancyResponseDto toVacancyDto(HHVacancyResponseDto hhVacancy);
}
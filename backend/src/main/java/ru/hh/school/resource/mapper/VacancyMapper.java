package ru.hh.school.resource.mapper;

import org.mapstruct.Mapper;
import ru.hh.school.resource.dto.*;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    VacanciesResponseDto toVacanciesDto(HHVacanciesResponseDto hhVacancies);

    VacancyResponseDto toVacancyDto(HHVacancyResponseDto hhVacancy);
}
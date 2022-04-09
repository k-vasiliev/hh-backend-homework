package ru.hh.school.service;

import ru.hh.school.resource.dto.HHVacanciesResponseDto;
import ru.hh.school.resource.dto.HHVacancyResponseDto;

public interface VacancyService {
    HHVacanciesResponseDto getHHVacancies(String query, Integer page, Integer perPage);

    HHVacancyResponseDto getHHVacancyById(Long vacancyId);
}
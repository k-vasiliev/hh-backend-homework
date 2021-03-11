package ru.hh.school.mapper;

import ru.hh.school.dto.response.FavoritesVacancyResponseDto;
import ru.hh.school.entity.FavoritesVacancy;

import javax.inject.Singleton;

@Singleton
public class FavoritesVacancyMapper {

    private final AreaMapper areaMapper;

    private final EmployerMapper employerMapper;

    public FavoritesVacancyMapper(AreaMapper areaMapper, EmployerMapper employerMapper) {
        this.areaMapper = areaMapper;
        this.employerMapper = employerMapper;
    }

    public FavoritesVacancyResponseDto map(FavoritesVacancy vacancy) {
        return vacancy == null ? null : new FavoritesVacancyResponseDto(
                vacancy.getVacancyId(),
                vacancy.getVacancy() == null ? null : vacancy.getVacancy().getName(),
                vacancy.getCreated() == null ? null : vacancy.getCreated().toString(),
                areaMapper.map(vacancy.getVacancy() == null ? null : vacancy.getVacancy().getArea()),
                employerMapper.map(vacancy.getVacancy() == null ? null : vacancy.getVacancy().getEmployer()),
                vacancy.getComment(),
                vacancy.getPopularityValue(vacancy.getViewsCount()),
                vacancy.getViewsCount());
    }
}

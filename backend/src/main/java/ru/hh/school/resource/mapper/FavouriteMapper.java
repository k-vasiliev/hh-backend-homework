package ru.hh.school.resource.mapper;

import org.mapstruct.Mapper;
import ru.hh.school.domain.Employer;
import ru.hh.school.domain.Favourite;
import ru.hh.school.domain.Vacancy;
import ru.hh.school.resource.dto.*;

@Mapper(componentModel = "spring")
public interface FavouriteMapper {
    FavouriteEmployerResponseDto toFavoriteEmployerResponseDto(Favourite favourite);

    FavouriteVacancyResponseDto toFavouriteVacancyResponseDto(Favourite favourite);

    EmployersResponseDto toFavoriteEmployerDto(Employer employer);

    VacancyResponseDto toFavoriteVacancyDto(Vacancy Vacancy);
}
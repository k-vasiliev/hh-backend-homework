package ru.hh.school.resource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hh.school.domain.Employer;
import ru.hh.school.domain.Favourite;
import ru.hh.school.domain.Vacancy;
import ru.hh.school.resource.dto.EmployerResponseDto;
import ru.hh.school.resource.dto.FavouriteEmployerResponseDto;
import ru.hh.school.resource.dto.FavouriteVacancyResponseDto;
import ru.hh.school.resource.dto.VacancyResponseDto;

@Mapper(componentModel = "spring", uses = {AreaMapper.class})
public interface FavouriteMapper {
    String POPULARITY_CONVERT_FUNCTION = "java(favourite.getViewsCount() > 50 ? \"POPULAR\" : \"REGULAR\")";

    @Mapping(source = "linkId", target = "id")
    @Mapping(source = "employer.name", target = "name")
    @Mapping(source = "employer.description", target = "description")
    @Mapping(target = "popularity", expression = POPULARITY_CONVERT_FUNCTION)
    FavouriteEmployerResponseDto toFavoriteEmployerResponseDto(Favourite favourite);

    @Mapping(source = "linkId", target = "id")
    @Mapping(source = "vacancy.name", target = "name")
    @Mapping(source = "vacancy.salary", target = "salary")
    @Mapping(source = "vacancy.employer", target = "employer")
    @Mapping(source = "vacancy.createdAt", target = "createdAt")
    @Mapping(target = "popularity", expression = POPULARITY_CONVERT_FUNCTION)
    FavouriteVacancyResponseDto toFavouriteVacancyResponseDto(Favourite favourite);

    EmployerResponseDto toFavoriteEmployerDto(Employer employer);

    VacancyResponseDto toFavoriteVacancyDto(Vacancy vacancy);
}
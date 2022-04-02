package ru.hh.school.resource;

import ru.hh.school.resource.dto.PaginationResponseDto;

import java.util.List;

public class FavouriteVacanciesResponseDto extends PaginationResponseDto<FavouriteVacanciesResponseData> {

    public FavouriteVacanciesResponseDto() {
    }

    public FavouriteVacanciesResponseDto(List<FavouriteVacanciesResponseData> data) {
        super(data);
    }
}

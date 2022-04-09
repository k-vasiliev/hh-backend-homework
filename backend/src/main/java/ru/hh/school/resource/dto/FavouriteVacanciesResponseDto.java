package ru.hh.school.resource.dto;

import java.util.List;

public class FavouriteVacanciesResponseDto extends PaginationResponseDto<FavouriteVacancyResponseDto> {

    public FavouriteVacanciesResponseDto() {
    }

    public FavouriteVacanciesResponseDto(List<FavouriteVacancyResponseDto> data, Long pages, Integer perPage, Integer page) {
        super(data, pages, perPage, page);
    }
}

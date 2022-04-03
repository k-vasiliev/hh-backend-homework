package ru.hh.school.resource.dto;

import java.util.List;

public class FavouriteVacanciesResponseDto extends PaginationResponseDto<FavouriteVacanciesResponseData> {

    public FavouriteVacanciesResponseDto() {
    }

    public FavouriteVacanciesResponseDto(List<FavouriteVacanciesResponseData> data, Integer pages, Integer perPage, Integer page) {
        super(data, pages, perPage, page);
    }
}

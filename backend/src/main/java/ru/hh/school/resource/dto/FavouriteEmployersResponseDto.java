package ru.hh.school.resource.dto;

import java.util.List;

public class FavouriteEmployersResponseDto extends PaginationResponseDto<FavouriteEmployersResponseData> {
    public FavouriteEmployersResponseDto() {
    }

    public FavouriteEmployersResponseDto(List<FavouriteEmployersResponseData> data, Integer pages, Integer perPage, Integer page) {
        super(data, pages, perPage, page);
    }
}

package ru.hh.school.resource.dto;

import java.util.List;

public class FavouriteEmployersResponseDto extends PaginationResponseDto<FavouriteEmployerResponseDto> {
    public FavouriteEmployersResponseDto() {
    }

    public FavouriteEmployersResponseDto(List<FavouriteEmployerResponseDto> data, Long pages, Integer perPage, Integer page) {
        super(data, pages, perPage, page);
    }
}

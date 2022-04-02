package ru.hh.school.resource.dto;

import java.util.List;

public class FavouriteEmployersResponseDto extends PaginationResponseDto<FavouriteEmployersResponseData> {
    public FavouriteEmployersResponseDto() {
    }

    public FavouriteEmployersResponseDto(List<FavouriteEmployersResponseData> data) {
        super(data);
    }
}

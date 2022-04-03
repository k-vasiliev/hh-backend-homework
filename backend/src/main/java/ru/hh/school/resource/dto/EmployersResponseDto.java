package ru.hh.school.resource.dto;

import java.util.List;

public class EmployersResponseDto extends PaginationResponseDto<EmployersResponseData> {
    public EmployersResponseDto() {
    }

    public EmployersResponseDto(List<EmployersResponseData> data, Integer pages, Integer perPage, Integer page) {
        super(data, pages, perPage, page);
    }
}

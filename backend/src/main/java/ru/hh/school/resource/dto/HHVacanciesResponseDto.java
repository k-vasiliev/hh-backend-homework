package ru.hh.school.resource.dto;

import java.util.List;

public class HHVacanciesResponseDto extends PaginationResponseDto<VacanciesResponseData> {
    public HHVacanciesResponseDto() {
    }

    public HHVacanciesResponseDto(List<VacanciesResponseData> data, Long pages, Integer perPage, Integer page) {
        super(data, pages, perPage, page);
    }
}

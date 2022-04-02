package ru.hh.school.resource.dto;

import java.util.List;

public class VacanciesResponseDto extends PaginationResponseDto<VacanciesResponseData> {
    public VacanciesResponseDto() {
    }

    public VacanciesResponseDto(List<VacanciesResponseData> data) {
        super(data);
    }
}

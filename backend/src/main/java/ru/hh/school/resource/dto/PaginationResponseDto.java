package ru.hh.school.resource.dto;

import java.util.List;

public class PaginationResponseDto<T> {
    private List<T> data;

    public PaginationResponseDto() {
    }

    public PaginationResponseDto(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

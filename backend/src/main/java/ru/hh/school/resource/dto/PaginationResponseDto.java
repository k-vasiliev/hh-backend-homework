package ru.hh.school.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaginationResponseDto<T> {
    private List<T> items;
    private Integer pages;
    @JsonProperty("per_page")
    private Integer perPage;
    private Integer page;

    public PaginationResponseDto() {
    }

    public PaginationResponseDto(List<T> data, Integer pages, Integer perPage, Integer page) {
        this.items = data;
        this.pages = pages;
        this.perPage = perPage;
        this.page = page;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}

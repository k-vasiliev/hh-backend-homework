package ru.hh.school.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaginationResponseDto<T> {
    private List<T> items;

    @JsonProperty("item_count")
    private Long itemCount;

    private Integer page;

    @JsonProperty("per_page")
    private Integer perPage;

    public PaginationResponseDto() {
    }

    public PaginationResponseDto(List<T> data, Long pages, Integer perPage, Integer page) {
        this.items = data;
        this.itemCount = pages;
        this.perPage = perPage;
        this.page = page;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
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

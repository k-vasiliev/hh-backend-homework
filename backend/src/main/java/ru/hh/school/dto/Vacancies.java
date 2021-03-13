package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Vacancies {

    private List<Vacancy> items;

    private Integer pages;

    @JsonProperty("per_page")
    private Integer perPage;

    private Integer page;

    public Vacancies() {
    }

    public Vacancies(List<Vacancy> items, Integer pages, Integer perPage, Integer page) {
        this.items = items;
        this.pages = pages;
        this.perPage = perPage;
        this.page = page;
    }

    public List<Vacancy> getItems() {
        return items;
    }

    public void setItems(List<Vacancy> items) {
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

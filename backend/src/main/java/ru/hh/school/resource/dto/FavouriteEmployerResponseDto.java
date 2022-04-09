package ru.hh.school.resource.dto;

import java.time.LocalDateTime;

public class FavouriteEmployerResponseDto {
    private Long id; // идентификатор компании
    private String name; // название комопании
    private String description; // описание компании
    private AreaData area; // регион компании
    private LocalDateTime dateCreate;
    private String popularity; // популярность компании. Для компаний с более чем 50 просмотров выводить `POPULAR`, для остальных `REGULAR`
    private String comment;
    private Long viewsCount;

    public FavouriteEmployerResponseDto() {
    }

    public FavouriteEmployerResponseDto(Long id, String name, String description, AreaData area, LocalDateTime dateCreate, String popularity, String comment, Long viewsCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
        this.dateCreate = dateCreate;
        this.popularity = popularity;
        this.comment = comment;
        this.viewsCount = viewsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AreaData getArea() {
        return area;
    }

    public void setArea(AreaData area) {
        this.area = area;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }
}

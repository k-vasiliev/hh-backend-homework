package ru.hh.school.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class FavouriteEmployersResponseData {
    private Long id;
    private String name;
    @JsonProperty(value = "date_create")
    private Date dateCreate; // дата добавления в избранное
    private String description;
    private String area;
    private String comment;
    private FavouriteEmployerPopularity popularity; // популярность компании
    @JsonProperty(value = "views_count")
    private Long viewsCount; // количество просмотров

    public FavouriteEmployersResponseData() {
    }

    public FavouriteEmployersResponseData(Long id, String name, Date dateCreate, String description, String area, String comment, FavouriteEmployerPopularity popularity, Long viewsCount) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
        this.description = description;
        this.area = area;
        this.comment = comment;
        this.popularity = popularity;
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public FavouriteEmployerPopularity getPopularity() {
        return popularity;
    }

    public void setPopularity(FavouriteEmployerPopularity popularity) {
        this.popularity = popularity;
    }

    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }
}

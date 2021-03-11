package ru.hh.school.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.EmployerApiHh;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoritesVacancyResponseDto {

    private Integer id;

    private String name;

    @JsonProperty(value = "date_create")
    private String dateCreate;

    private AreaDto area;

    private EmployerApiHh employer;

    private String comment;

    private String popularity;

    @JsonProperty(value = "views_count")
    private Integer viewsCount;


    public FavoritesVacancyResponseDto(Integer id) {
        this.id = id;
    }

    public FavoritesVacancyResponseDto(Integer id, String name, String dateCreate, AreaDto area, EmployerApiHh employer, String comment, String popularity, Integer viewsCount) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
        this.area = area;
        this.employer = employer;
        this.comment = comment;
        this.popularity = popularity;
        this.viewsCount = viewsCount;
    }

    public FavoritesVacancyResponseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }


    public void setEmployer(EmployerApiHh employer) {
        this.employer = employer;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public void clearWithoutId() {
        setName(null);
        setDateCreate(null);
        setEmployer(null);
        setArea(null);
        setComment(null);
        setPopularity(null);
        setViewsCount(null);
    }

    public String getName() {
        return name;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public AreaDto getArea() {
        return area;
    }

    public EmployerApiHh getEmployer() {
        return employer;
    }

    public String getComment() {
        return comment;
    }

    public String getPopularity() {
        return popularity;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }
}

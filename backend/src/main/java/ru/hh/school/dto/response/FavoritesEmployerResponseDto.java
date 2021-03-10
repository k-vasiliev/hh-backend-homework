package ru.hh.school.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.dto.AreaDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoritesEmployerResponseDto {

    private Integer id;

    private String name;

    @JsonProperty(value = "date_create")
    private String dateCreate;

    private String description;

    private AreaDto area;

    private String comment;

    private String popularity;

    @JsonProperty(value = "views_count")
    private Integer viewsCount;

    public FavoritesEmployerResponseDto(Integer id, String name, String dateCreate, String description, AreaDto area, String comment, String popularity, Integer viewsCount) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
        this.description = description;
        this.area = area;
        this.comment = comment;
        this.popularity = popularity;
        this.viewsCount = viewsCount;
    }

    public FavoritesEmployerResponseDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getDescription() {
        return description;
    }

    public AreaDto getArea() {
        return area;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArea(AreaDto area) {
        this.area = area;
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

    public void ClearWithoutId() {
        setName(null);
        setDateCreate(null);
        setDescription(null);
        setArea(null);
        setComment(null);
        setPopularity(null);
        setViewsCount(null);
    }
}

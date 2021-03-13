package ru.hh.school.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.dto.area.AreaDto;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDtoRequest {
    public enum Popularity {
        POPULAR,
        REGULAR
    }

    private Integer id;
    private String name;
    private LocalDate creationDate;
    private String description;
    private AreaDto area;
    private String comment;
    private Popularity popularity;
    private Integer views_count;

    public CompanyDtoRequest(Integer id, String name, LocalDate creationDate, String description, AreaDto area, String comment, Popularity popularity, Integer views_count) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
        this.area = area;
        this.comment = comment;
        this.popularity = popularity;
        this.views_count = views_count;
    }

    public CompanyDtoRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AreaDto getArea() {
        return area;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public Integer getViews_count() {
        return views_count;
    }

    public void setViews_count(Integer views_count) {
        this.views_count = views_count;
    }
}

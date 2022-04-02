package ru.hh.school.resource;

import java.util.Date;

public class FavouriteVacanciesResponseData {
    private String id;
    private String name;
    private Date date_create; // дата добавления в избранное
    private String area;
    private Double salary;
    private Date created_at;
    private Long employer;
    private Long popularity; // та же логика, что и в компаниях
    private Long views_count;
    private String comment;

    public FavouriteVacanciesResponseData() {
    }

    public FavouriteVacanciesResponseData(String id, String name, Date date_create, String area, Double salary, Date created_at, Long employer, Long popularity, Long views_count, String comment) {
        this.id = id;
        this.name = name;
        this.date_create = date_create;
        this.area = area;
        this.salary = salary;
        this.created_at = created_at;
        this.employer = employer;
        this.popularity = popularity;
        this.views_count = views_count;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Long getEmployer() {
        return employer;
    }

    public void setEmployer(Long employer) {
        this.employer = employer;
    }

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }

    public Long getViews_count() {
        return views_count;
    }

    public void setViews_count(Long views_count) {
        this.views_count = views_count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

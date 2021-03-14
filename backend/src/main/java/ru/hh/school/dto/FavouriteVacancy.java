package ru.hh.school.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FavouriteVacancy {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final Integer POPULAR_THRESHOLD = 50;

    public Integer id;

    public String name;

    public Area area;

    public Salary salary;

    public String created_at;

    public Employer employer;

    public String date_create;

    public String comment;

    public Popularity popularity;

    public Integer views_count;

    public static FavouriteVacancy fromEntity(ru.hh.school.entity.FavouriteVacancy entityVacancy) {
        FavouriteVacancy res = new FavouriteVacancy();
        res.id = entityVacancy.getId();
        res.name = entityVacancy.getName();
        Area resArea = new Area();
        resArea.id = entityVacancy.getArea().getId();
        resArea.name = entityVacancy.getArea().getName();
        res.area = resArea;
        res.created_at = entityVacancy.getCreatedAt().format(DATE_FORMATTER);
        Employer resEmployer = new Employer();
        resEmployer.id = entityVacancy.getEmployerId();
        resEmployer.name = entityVacancy.getEmployerName();
        res.employer = resEmployer;
        res.date_create = entityVacancy.getCreationTime().format(DATE_FORMATTER);
        res.comment = entityVacancy.getComment();
        if (entityVacancy.getViewsCount() > POPULAR_THRESHOLD) {
            res.popularity = Popularity.POPULAR;
        } else {
            res.popularity = Popularity.REGULAR;
        }
        res.views_count = entityVacancy.getViewsCount();
        return res;
    }

}

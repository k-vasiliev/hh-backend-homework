package ru.hh.school.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FavouriteEmployer {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final Integer POPULAR_THRESHOLD = 50;

    public Integer id;

    public String name;

    public String description;

    public Area area;

    public String date_create;

    public String comment;

    public Popularity popularity;

    public Integer views_count;

    public static FavouriteEmployer fromEntity(ru.hh.school.entity.FavouriteEmployer entityEmployer) {
        FavouriteEmployer res = new FavouriteEmployer();
        res.id = entityEmployer.getId();
        res.name = entityEmployer.getName();
        res.description = entityEmployer.getDescription();
        Area resArea = new Area();
        resArea.id = entityEmployer.getArea().getId();
        resArea.name = entityEmployer.getArea().getName();
        res.area = resArea;
        res.date_create = entityEmployer.getDateCreate().format(DATE_FORMATTER);
        res.comment = entityEmployer.getComment();
        if (entityEmployer.getViewsCount() > POPULAR_THRESHOLD) {
            res.popularity = Popularity.POPULAR;
        } else {
            res.popularity = Popularity.REGULAR;
        }
        res.views_count = entityEmployer.getViewsCount();
        return res;
    }
}

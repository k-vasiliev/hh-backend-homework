package ru.hh.school.view;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import ru.hh.school.entity.Area;
import ru.hh.school.entity.FavoriteEmployer;

public class FavoriteEmployerView {

  private Integer id;

  private String name;

  @JsonProperty("date_create")
  private OffsetDateTime dateCreate;

  private String description;

  private Area area;

  private String comment;

  @JsonProperty("views_count")
  private Integer viewsCount;

  private Popularity popularity;

  public FavoriteEmployerView(FavoriteEmployer employer) {
    id = employer.getId();
    name = employer.getName();
    dateCreate = employer.getCreationTime();
    description = employer.getDescription();
    area = employer.getArea();
    comment = employer.getComment();
    viewsCount = employer.getViewsCount();
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public OffsetDateTime getDateCreate() {
    return dateCreate;
  }

  public String getDescription() {
    return description;
  }

  public Area getArea() {
    return area;
  }

  public String getComment() {
    return comment;
  }

  public Integer getViewsCount() {
    return viewsCount;
  }

  public Popularity getPopularity() {
    return popularity;
  }

  public void setPopularity(Popularity popularity) {
    this.popularity = popularity;
  }
}

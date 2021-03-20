package ru.hh.school.view;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import ru.hh.school.entity.Area;
import ru.hh.school.entity.FavoriteVacancy;

public class FavoriteVacancyView {

  private Integer id;

  private String name;

  @JsonProperty("date_create")
  private OffsetDateTime dateCreate;

  private Area area;

  private Salary salary;

  @JsonProperty("created_at")
  private OffsetDateTime createdAt;

  private EmployerShortView employer;

  private Popularity popularity;

  @JsonProperty("views_count")
  private Integer viewsCount;

  private String comment;

  public FavoriteVacancyView(FavoriteVacancy vacancy) {
    id = vacancy.getId();
    name = vacancy.getName();
    dateCreate = vacancy.getArchivingTime();
    area = vacancy.getArea();
    salary = Salary.getSalaryOrNull(vacancy.getCompensationFrom(), vacancy.getCompensationTo(),
      vacancy.getCompensationGross(), vacancy.getCompensationCurrency());;
    createdAt = vacancy.getCreationTime();
    employer = new EmployerShortView(vacancy.getEmployer());
    viewsCount = vacancy.getViewsCount();
    comment = vacancy.getComment();
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

  public Area getArea() {
    return area;
  }

  public Salary getSalary() {
    return salary;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public EmployerShortView getEmployer() {
    return employer;
  }

  public Popularity getPopularity() {
    return popularity;
  }

  public void setPopularity(Popularity popularity) {
    this.popularity = popularity;
  }

  public Integer getViewsCount() {
    return viewsCount;
  }

  public String getComment() {
    return comment;
  }
}

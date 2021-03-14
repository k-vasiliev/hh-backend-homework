package ru.hh.school.entity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "favorite_employer")
public class FavoriteEmployer {

  @Id
  @Column(name = "employer_id")
  private Integer id;

  @Column(name = "company_name")
  private String name;

  @Column(name = "company_description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "area_id")
  private Area area;

  @Column(name = "creation_time")
  private OffsetDateTime creationTime;

  @Column(name = "comment")
  private String comment;

  @Column(name = "views_count", columnDefinition = "integer default 0")
  private Integer viewsCount;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "employer", orphanRemoval = true)
  private List<FavoriteVacancy> vacancies = new ArrayList<>();

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public OffsetDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(OffsetDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Integer getViewsCount() {
    return viewsCount;
  }

  public void setViewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
  }

  public List<FavoriteVacancy> getVacancies() {
    return vacancies;
  }

  public void setVacancies(List<FavoriteVacancy> vacancies) {
    this.vacancies = vacancies;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FavoriteEmployer employer = (FavoriteEmployer) o;
    return Objects.equals(id, employer.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

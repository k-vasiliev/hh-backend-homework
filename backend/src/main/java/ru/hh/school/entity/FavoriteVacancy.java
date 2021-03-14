package ru.hh.school.entity;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favorite_vacancy")
public class FavoriteVacancy {

  @Id
  @Column(name = "vacancy_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id")
  private FavoriteEmployer employer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "area_id")
  private Area area;

  @Column(name = "vacancy_name")
  private String name;

  @Column(name = "compensation_from")
  private Integer compensationFrom;

  @Column(name = "compensation_to")
  private Integer compensationTo;

  @Column(name = "compensation_gross")
  private Boolean compensationGross;

  @Column(name = "compensation_currency")
  private String compensationCurrency;

  @Column(name = "creation_time")
  private OffsetDateTime creationTime;

  @Column(name = "archiving_time")
  private OffsetDateTime archivingTime;

  @Column(name = "views_count", columnDefinition = "integer default 0")
  private Integer viewsCount;

  private String comment;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public FavoriteEmployer getEmployer() {
    return employer;
  }

  public void setEmployer(FavoriteEmployer employer) {
    this.employer = employer;
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCompensationFrom() {
    return compensationFrom;
  }

  public void setCompensationFrom(Integer compensationFrom) {
    this.compensationFrom = compensationFrom;
  }

  public Integer getCompensationTo() {
    return compensationTo;
  }

  public void setCompensationTo(Integer compensationTo) {
    this.compensationTo = compensationTo;
  }

  public Boolean getCompensationGross() {
    return compensationGross;
  }

  public void setCompensationGross(Boolean compensationGross) {
    this.compensationGross = compensationGross;
  }

  public String getCompensationCurrency() {
    return compensationCurrency;
  }

  public void setCompensationCurrency(String compensationCurrency) {
    this.compensationCurrency = compensationCurrency;
  }

  public OffsetDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(OffsetDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public OffsetDateTime getArchivingTime() {
    return archivingTime;
  }

  public void setArchivingTime(OffsetDateTime archivingTime) {
    this.archivingTime = archivingTime;
  }

  public Integer getViewsCount() {
    return viewsCount;
  }

  public void setViewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FavoriteVacancy vacancy = (FavoriteVacancy) o;
    return Objects.equals(id, vacancy.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

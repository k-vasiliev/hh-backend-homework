package ru.hh.school.entity;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "vacancy")
public class Vacancy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vacancy_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id")
  private Employer employer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "area_id")
  private Area area;

  @Column(name = "name")
  private String name;

  @Column(name = "compensation_from")
  private Integer compensationFrom;

  @Column(name = "compensation_to")
  private Integer compensationTo;

  @Column(name = "compensation_gross")
  private Boolean compensationGross;

  @Column(name = "compensation_currency")
  private String compensationCurrency;

  @JsonProperty("created_at")
  @Column(name = "creation_time")
  private OffsetDateTime creationTime;

  @Column(name = "archiving_time")
  private OffsetDateTime archivingTime;

  public Vacancy() {}

  public Vacancy(@JsonProperty("salary") JsonNode salary) {
    if (salary.isNull())
      return;
    compensationCurrency = salary.get("currency").isNull() ? null : salary.get("currency").textValue();
    compensationFrom = salary.get("from").isNull() ? null : salary.get("from").intValue();
    compensationTo = salary.get("to").isNull() ? null : salary.get("to").intValue();
    compensationGross = salary.get("gross").isNull() ? null : salary.get("gross").booleanValue();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vacancy vacancy = (Vacancy) o;
    return Objects.equals(id, vacancy.id);
  }

  @Override
  public int hashCode() {
    return 17;
  }
}

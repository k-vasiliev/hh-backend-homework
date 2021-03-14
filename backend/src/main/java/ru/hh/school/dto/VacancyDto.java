package ru.hh.school.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class VacancyDto {

  private Integer id;

  private String name;

  private AreaDto area;

  private Integer compensationFrom;

  private Integer compensationTo;

  private Boolean compensationGross;

  private String compensationCurrency;

  private OffsetDateTime creationTime;

  private EmployerDto employer;

  public VacancyDto() {}

  public VacancyDto(@JsonProperty("salary") JsonNode salary) {
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AreaDto getArea() {
    return area;
  }

  public void setArea(AreaDto area) {
    this.area = area;
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

  public EmployerDto getEmployer() {
    return employer;
  }

  public void setEmployer(EmployerDto employer) {
    this.employer = employer;
  }
}

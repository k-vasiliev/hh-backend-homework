package ru.hh.school.view;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.VacancyDto;

public class VacancyShortView {

  private Integer id;

  private String name;

  private AreaDto area;

  private Salary salary;

  @JsonProperty("created_at")
  private OffsetDateTime createdAt;

  private EmployerShortView employer;

  public VacancyShortView(VacancyDto vacancy) {
    id = vacancy.getId();
    name = vacancy.getName();
    area = vacancy.getArea();
    Integer from = vacancy.getCompensationFrom();
    Integer to = vacancy.getCompensationTo();
    String currency = vacancy.getCompensationCurrency();
    Boolean gross = vacancy.getCompensationGross();
    salary = (currency != null || from != null || to != null || gross != null) ?
      new Salary(from, to, gross, currency) : null;
    createdAt = vacancy.getCreationTime();
    employer = new EmployerShortView(vacancy.getEmployer());
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public AreaDto getArea() {
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
}

class Salary {

  private Integer from;

  private Integer to;

  private Boolean gross;

  private String currency;

  public Salary(Integer from, Integer to, Boolean gross, String currency) {
    this.from = from;
    this.to = to;
    this.gross = gross;
    this.currency = currency;
  }

  public Integer getFrom() {
    return from;
  }

  public Integer getTo() {
    return to;
  }

  public Boolean getGross() {
    return gross;
  }

  public String getCurrency() {
    return currency;
  }
}

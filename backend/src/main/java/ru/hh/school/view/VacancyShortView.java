package ru.hh.school.view;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import ru.hh.school.dto.AreaData;
import ru.hh.school.dto.VacancyData;

public class VacancyShortView {

  private Integer id;

  private String name;

  private AreaData area;

  private Salary salary;

  @JsonProperty("created_at")
  private OffsetDateTime createdAt;

  private EmployerShortView employer;

  public VacancyShortView(VacancyData vacancy) {
    id = vacancy.getId();
    name = vacancy.getName();
    area = vacancy.getArea();
    salary = Salary.getSalaryOrNull(vacancy.getCompensationFrom(), vacancy.getCompensationTo(),
      vacancy.getCompensationGross(), vacancy.getCompensationCurrency());
    createdAt = vacancy.getCreationTime();
    employer = new EmployerShortView(vacancy.getEmployer());
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public AreaData getArea() {
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

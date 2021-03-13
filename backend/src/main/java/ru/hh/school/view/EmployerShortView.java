package ru.hh.school.view;

import ru.hh.school.entity.Employer;

public class EmployerShortView {

  private Integer id;

  private String name;

  public EmployerShortView(Employer employer) {
    id = employer.getId();
    name = employer.getName();
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}

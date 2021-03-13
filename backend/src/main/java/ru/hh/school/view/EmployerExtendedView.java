package ru.hh.school.view;

import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;

public class EmployerExtendedView extends EmployerShortView {

  private String description;

  private Area area;

  public EmployerExtendedView(Employer employer) {
    super(employer);
    description = employer.getDescription();
    area = employer.getArea();
  }

  public String getDescription() {
    return description;
  }

  public Area getArea() {
    return area;
  }
}

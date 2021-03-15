package ru.hh.school.view;

import ru.hh.school.dto.AreaData;
import ru.hh.school.dto.EmployerData;

public class EmployerExtendedView extends EmployerShortView {

  private String description;

  private AreaData area;

  public EmployerExtendedView(EmployerData employer) {
    super(employer);
    description = employer.getDescription();
    area = employer.getArea();
  }

  public String getDescription() {
    return description;
  }

  public AreaData getArea() {
    return area;
  }
}

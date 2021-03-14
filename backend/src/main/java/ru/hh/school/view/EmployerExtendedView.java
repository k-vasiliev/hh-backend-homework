package ru.hh.school.view;

import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.EmployerDto;

public class EmployerExtendedView extends EmployerShortView {

  private String description;

  private AreaDto area;

  public EmployerExtendedView(EmployerDto employer) {
    super(employer);
    description = employer.getDescription();
    area = employer.getArea();
  }

  public String getDescription() {
    return description;
  }

  public AreaDto getArea() {
    return area;
  }
}

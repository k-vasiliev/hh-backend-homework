package ru.hh.school.dto;

import java.time.OffsetDateTime;

public interface VacancyData {

  Integer getId();

  String getName();

  AreaData getArea();

  Integer getCompensationFrom();

  Integer getCompensationTo();

  Boolean getCompensationGross();

  String getCompensationCurrency();

  OffsetDateTime getCreationTime();

  EmployerData getEmployer();
}

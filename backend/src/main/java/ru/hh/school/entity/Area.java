package ru.hh.school.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ru.hh.school.dto.AreaData;

@Entity
@Table(name = "area")
public class Area implements AreaData {

  @Id
  @Column(name = "area_id")
  private Integer id;

  @Column(name = "area_name")
  private String name;

  public Area() {}

  public Area(AreaData data) {
    id = data.getId();
    name = data.getName();
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

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Area area = (Area) o;
    return Objects.equals(id, area.id);
  }
}

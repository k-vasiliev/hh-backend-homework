package ru.hh.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ru.hh.school.dto.AreaData;

@Entity
@Table(name = "area")
public class Area {

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
}

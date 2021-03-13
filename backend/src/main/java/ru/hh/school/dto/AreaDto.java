package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.entity.Area;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaDto {

    private int id;
    private String name;

    public AreaDto() {}

    public AreaDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AreaDto[id=" + id + ", name=" + name + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaDto) || o == null) return false;
        AreaDto areaDto = (AreaDto) o;
        return id == areaDto.id &&
                Objects.equals(name, areaDto.name);
    }

}

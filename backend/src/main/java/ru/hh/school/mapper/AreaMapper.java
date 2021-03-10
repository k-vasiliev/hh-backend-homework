package ru.hh.school.mapper;

import ru.hh.school.dto.AreaDto;
import ru.hh.school.entity.Area;

import javax.inject.Singleton;

@Singleton
public class AreaMapper {

    public AreaDto map(Area area) {
        return area == null ? null : new AreaDto(
                area.getId(),
                area.getName());
    }

    public Area map(AreaDto area) {
        return area == null ? null : new Area(
                area.getId(),
                area.getName());
    }
}

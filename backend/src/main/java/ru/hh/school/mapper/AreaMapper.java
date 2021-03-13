package ru.hh.school.mapper;

import ru.hh.school.dto.area.AreaDto;
import ru.hh.school.entity.Area;

import javax.inject.Singleton;

@Singleton
public class AreaMapper {

    public AreaDto map(Area area) {
        return new AreaDto(
                area.getId(),
                area.getName()
        );
    }

    public Area map(AreaDto area) {
        return new Area(
                area.getId(),
                area.getName()
        );
    }
}

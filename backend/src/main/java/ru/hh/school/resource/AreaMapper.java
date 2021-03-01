package ru.hh.school.resource;

import ru.hh.school.dto.AreaDto;
import ru.hh.school.entity.AreaEntity;

public final class AreaMapper {

    public static AreaDto map(AreaEntity areaEntity) {
        return new AreaDto(areaEntity.getId(), areaEntity.getName());
    }
}

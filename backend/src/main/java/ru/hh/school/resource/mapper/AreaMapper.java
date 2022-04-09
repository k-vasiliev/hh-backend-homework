package ru.hh.school.resource.mapper;

import org.mapstruct.Mapper;
import ru.hh.school.resource.dto.AreaData;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    AreaData toAreaData(AreaData areaData);
}
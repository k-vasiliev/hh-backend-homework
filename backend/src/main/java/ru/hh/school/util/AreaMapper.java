package ru.hh.school.util;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.entity.Area;

@Service
public class AreaMapper {

    public AreaDto mapToDto(Area area) {
        return new AreaDto(area.getId(), area.getName());
    }

    public Area mapToEntity(AreaDto areaDto) {
        Area area = new Area();
        area.setId(areaDto.getId());
        area.setName(areaDto.getName());
        return area;
    }

}

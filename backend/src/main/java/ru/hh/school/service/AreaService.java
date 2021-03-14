package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.entity.Area;

import javax.ws.rs.NotFoundException;

@Service
public class AreaService {

    private final AreaDao areaDao;

    AreaService(AreaDao areaDao) {
        this.areaDao = areaDao;
    }

    public Area getArea(Integer id) {
        return areaDao.get(Area.class, id).orElseThrow(NotFoundException::new);
    }

    public Area getOrCreateArea(Area area) {
        try {
            return getArea(area.getId());
        } catch (NotFoundException e) {
            areaDao.save(area);
            return area;
        }
    }

}

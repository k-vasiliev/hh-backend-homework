package ru.hh.school.service;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.entity.Area;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class AreaService {

    private AreaDao areaDao;

    public AreaService(AreaDao userDao) {
        this.areaDao = userDao;
    }

    @Transactional
    public Area getArea(Integer id) throws NotFoundException {
        Optional<Area> area = areaDao.getArea(id);
        if (area.isEmpty())
            throw new NotFoundException("Area not found with id = " + id);
        else
            return area.get();
    }

    @Transactional
    public Area getAreaById(Integer id) throws NotFoundException {
        Optional<Area> area = areaDao.getAreaByAreaId(id);
        if (area.isEmpty())
            throw new NotFoundException("Area not found with id = " + id);
        else
            return area.get();
    }

    @Transactional
    public List<Area> getAreas() {
        return areaDao.getAreas();
    }


    @Transactional
    public Integer save(Area area) {
        return areaDao.save(area);
    }

    @Transactional
    public Integer saveOrUpdate(Area area) {
        return areaDao.saveOrUpdate(area);
    }

}

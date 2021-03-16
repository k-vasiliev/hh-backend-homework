package ru.hh.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.EmployerDetailed;
import ru.hh.school.entity.AreaEntity;
import ru.hh.school.entity.EmployerEntity;
import ru.hh.school.entity.Popularity;
import ru.hh.school.request.FavoritesEmployerRequest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoritesEmployerService {

    private static final Logger logger = LoggerFactory.getLogger(FavoritesEmployerService.class);

    private HhService employerService;

    private EmployerDao employerDao;

    private FileSettings fileSettings;

    public FavoritesEmployerService(HhService employerService, EmployerDao employerDao, FileSettings fileSettings) {
        this.employerService = employerService;
        this.employerDao = employerDao;
        this.fileSettings = fileSettings;
    }

    @Transactional
    public void addEmployer(FavoritesEmployerRequest employer) {
        EmployerDetailed employerDetailed = employerService.getEmployer(employer.getEmployerId());

        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setId(employerDetailed.getArea().getId());
        areaEntity.setName(employerDetailed.getArea().getName());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setId(employerDetailed.getId());
        employerEntity.setName(employerDetailed.getName());
        employerEntity.setDateCreate(LocalDateTime.now());
        employerEntity.setDescription(employerDetailed.getDescription());
        employerEntity.setArea(areaEntity);
        employerEntity.setComment(employer.getComment());
        employerEntity.setViewsCount(0);

        employerDao.saveOrUpdate(employerEntity);
    }

    @Transactional
    public List<EmployerEntity> getEmployers(Integer page, Integer perPage) {
        Integer popularityThreshold = fileSettings.getInteger("popularityThreshold");

        List<EmployerEntity> employerEntities = employerDao.getByPage(EmployerEntity.class, page, perPage);
        employerEntities.forEach(employerEntity -> {
            employerEntity.setViewsCount(employerEntity.getViewsCount() + 1);
            employerDao.update(employerEntity);
            employerDao.getSession().flush();
            if (employerEntity.getViewsCount() > popularityThreshold) {
                employerEntity.setPopularity(Popularity.POPULAR);
            } else {
                employerEntity.setPopularity(Popularity.REGULAR);
            }
        });

        return employerEntities;
    }

    @Transactional
    public void update(FavoritesEmployerRequest employer) {
        EmployerEntity employerEntity = employerDao.get(EmployerEntity.class, employer.getEmployerId());
        if (employerEntity != null) {
            employerEntity.setComment(employer.getComment());
            employerDao.update(employerEntity);
        }
    }

    @Transactional
    public void delete(Integer id) {
        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setId(id);
        employerDao.delete(employerEntity);
    }

    @Transactional
    public void refresh(Integer id) {
        EmployerEntity employerEntity = employerDao.get(EmployerEntity.class, id);
        if (employerEntity == null) {
            return;
        }
        EmployerDetailed employerDetailed = employerService.getEmployer(id);

        AreaEntity areaEntity = employerEntity.getArea();
        areaEntity.setId(employerDetailed.getArea().getId());
        areaEntity.setName(employerDetailed.getArea().getName());

        employerEntity.setName(employerDetailed.getName());
        employerEntity.setDescription(employerDetailed.getDescription());
        employerEntity.setArea(areaEntity);

        employerDao.update(employerEntity);
    }

}

package ru.hh.school.service;

import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.VacancyDAO;
import ru.hh.school.dto.VacancyDTO;
import ru.hh.school.entity.AreaEntity;
import ru.hh.school.entity.VacancyEmployerEntity;
import ru.hh.school.entity.VacancyEntity;
import ru.hh.school.enums.Popularity;
import ru.hh.school.request.FavoriteVacanciesRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public class FavoriteVacanciesService {

    private final MainService mainService;

    private final VacancyDAO vacancyDao;

    private final FileSettings fileSettings;

    public FavoriteVacanciesService(MainService mainService, VacancyDAO vacancyDao, FileSettings fileSettings) {
        this.mainService = mainService;
        this.vacancyDao = vacancyDao;
        this.fileSettings = fileSettings;
    }

    @Transactional
    public void addVacancy(FavoriteVacanciesRequest vacancyRequest) {
        VacancyDTO vacancy = mainService.getVacancy(vacancyRequest.getVacancyId());

        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setId(vacancy.getArea().getId());
        areaEntity.setName(vacancy.getArea().getName());

        VacancyEmployerEntity vacancyEmployerEntity = new VacancyEmployerEntity(
            vacancy.getEmployer().getId(),
            vacancy.getEmployer().getName());

        VacancyEntity vacancyEntity = new VacancyEntity();
        vacancyEntity.setId(vacancy.getId());
        vacancyEntity.setName(vacancy.getName());
        vacancyEntity.setDateCreate(LocalDateTime.now());
        vacancyEntity.setArea(areaEntity);
        if (vacancy.getSalary() != null) {
            vacancyEntity.setSalary(vacancy.getSalary().getFrom());
        }
        vacancyEntity.setCreatedAt(vacancy.getCreatedAt());
        vacancyEntity.setEmployer(vacancyEmployerEntity);
        vacancyEntity.setComment(vacancyRequest.getComment());
        vacancyEntity.setViewsCount(0);

        vacancyDao.saveOrUpdate(vacancyEntity);
    }

    @Transactional
    public List<VacancyEntity> getVacancies(Integer page, Integer perPage) {
        Integer popularityThreshold = fileSettings.getInteger("popularityThreshold");

        List<VacancyEntity> vacancyEntities = vacancyDao.getByPage(VacancyEntity.class, page, perPage);
        vacancyEntities.forEach(vacancyEntity -> {
            vacancyEntity.setViewsCount(vacancyEntity.getViewsCount()+1);
            vacancyDao.update(vacancyEntity);
            vacancyDao.getSession().flush();
            if (vacancyEntity.getViewsCount() > popularityThreshold) {
                vacancyEntity.setPopularity(Popularity.POPULAR);
            } else {
                vacancyEntity.setPopularity(Popularity.REGULAR);
            }
        });

        return vacancyEntities;
    }

    @Transactional
    public void update(FavoriteVacanciesRequest vacancyRequest) {
        VacancyEntity vacancyEntity = vacancyDao.get(VacancyEntity.class, vacancyRequest.getVacancyId());
        if (vacancyEntity != null) {
            vacancyEntity.setComment(vacancyRequest.getComment());
            vacancyDao.update(vacancyEntity);
        }
    }

    @Transactional
    public void delete(Integer id) {
        VacancyEntity vacancyEntity = new VacancyEntity();
        vacancyEntity.setId(id);
        vacancyDao.delete(vacancyEntity);
    }

    @Transactional
    public void refresh(Integer id) {
        VacancyEntity vacancyEntity = vacancyDao.get(VacancyEntity.class, id);

        if (vacancyEntity == null) {
            return;
        }

        VacancyDTO vacancy = mainService.getVacancy(id);

        AreaEntity areaEntity = vacancyEntity.getArea();
        areaEntity.setId(vacancy.getArea().getId());
        areaEntity.setName(vacancy.getArea().getName());

        VacancyEmployerEntity vacancyEmployerEntity = vacancyEntity.getEmployer();
        vacancyEmployerEntity.setId(vacancy.getEmployer().getId());
        vacancyEmployerEntity.setName(vacancy.getEmployer().getName());

        vacancyEntity.setName(vacancy.getName());
        vacancyEntity.setDateCreate(LocalDateTime.now());
        vacancyEntity.setArea(areaEntity);
        if (vacancy.getSalary() != null) {
            vacancyEntity.setSalary(vacancy.getSalary().getFrom());
        }
        vacancyEntity.setCreatedAt(vacancy.getCreatedAt());
        vacancyEntity.setEmployer(vacancyEmployerEntity);

        vacancyDao.update(vacancyEntity);
    }

}
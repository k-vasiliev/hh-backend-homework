package ru.hh.school.service;

import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.Vacancy;
import ru.hh.school.entity.AreaEntity;
import ru.hh.school.entity.VacancyEmployerEntity;
import ru.hh.school.entity.VacancyEntity;
import ru.hh.school.request.FavoritesVacancyRequest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public class FavoritesVacancyService {

    private VacancyService vacancyService;

    private VacancyDao vacancyDao;

    private FileSettings fileSettings;

    public FavoritesVacancyService(VacancyService vacancyService, VacancyDao vacancyDao, FileSettings fileSettings) {
        this.vacancyService = vacancyService;
        this.vacancyDao = vacancyDao;
        this.fileSettings = fileSettings;
    }

    @Transactional
    public void addVacancy(FavoritesVacancyRequest vacancyRequest) {
        Vacancy vacancy = vacancyService.get(vacancyRequest.getVacancyId());

        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setId(vacancy.getArea().getId());
        areaEntity.setName(vacancy.getArea().getName());

        VacancyEmployerEntity vacancyEmployerEntity = new VacancyEmployerEntity();
        vacancyEmployerEntity.setId(vacancy.getEmployer().getId());
        vacancyEmployerEntity.setName(vacancy.getEmployer().getName());

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
            if (vacancyEntity.getViewsCount() > popularityThreshold) {
                vacancyEntity.setPopularity("POPULAR");
            } else {
                vacancyEntity.setPopularity("REGULAR");
            }
        });

        return vacancyEntities;
    }

    @Transactional
    public void update(FavoritesVacancyRequest vacancyRequest) {
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

        Vacancy vacancy = vacancyService.get(id);

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

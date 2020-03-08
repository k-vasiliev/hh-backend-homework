package ru.hh.school.service;

import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.NewVacancyDto;
import ru.hh.school.entity.VacancyEntity;
import ru.hh.school.entity.VacancyResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VacancyService {
    private final VacancyDao vacancyDao;

    public VacancyService(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public List<VacancyEntity> getVacancies() {
        return vacancyDao.getVacancies();
    }

    @Transactional
    public VacancyEntity getVacancyById(Integer id) {
        return vacancyDao.getVacancyById(id);
    }

    @Transactional
    public List<VacancyResponseEntity> getVacancyResponses(Integer vacancyId) {
        return vacancyDao.getVacancyResponses(vacancyId);
    }

    @Transactional
    public Integer addNegotiation(Integer resumeId, Integer vacancyId) {
        return vacancyDao.addNegotiation(resumeId, vacancyId);
    }

    @Transactional
    public Integer addVacancy(NewVacancyDto newVacancy) {
        return vacancyDao.addVacancy(newVacancy);
    }
}

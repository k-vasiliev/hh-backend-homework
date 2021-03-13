package ru.hh.school.service;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class VacancyService {

    private VacancyDao vacancyDao;

    public VacancyService(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public Integer save(Vacancy vacancy) {
        return vacancyDao.save(vacancy);
    }

    @Transactional
    public void deleteVacancy(Integer id) {
        vacancyDao.deleteVacancy(id);
    }

    @Transactional
    public Vacancy getVacancyById(Integer id) throws NotFoundException {
        Optional<Vacancy> vacancy = vacancyDao.getVacancyByVacancyId(id);
        if (vacancy.isEmpty())
            throw new NotFoundException("Company not found");
        else
            return vacancy.get();
    }

    @Transactional
    public List<Vacancy> getVacancies() {
        return vacancyDao.getVacancies();
    }

    @Transactional
    public void incrementView(Integer id) {
        vacancyDao.incrementView(id);
    }

    @Transactional
    public void updateVacancy(Vacancy vacancy) {
        vacancyDao.updateVacancy(vacancy);
    }
}

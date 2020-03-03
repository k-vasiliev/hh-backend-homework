package ru.hh.backend.service;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.dao.VacancyDao;
import ru.hh.backend.entity.Vacancy;

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
    public Vacancy getVacancy(Integer id) throws NotFoundException {
        Optional<Vacancy> vacancy = vacancyDao.getVacancy(id);
        if (vacancy.isEmpty())
            throw new NotFoundException("Vacancy not found");
        else
            return vacancy.get();
    }

    @Transactional
    public List<Vacancy> getVacancies() {
        return vacancyDao.getVacancies();
    }

    @Transactional
    public Integer save(Vacancy vacancy) {
        return vacancyDao.save(vacancy);
    }
}

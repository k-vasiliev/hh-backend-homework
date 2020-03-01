package service;

import dao.ResumeDao;
import dao.VacancyDao;
import entity.ResumeEntity;
import entity.VacancyEntity;
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
}

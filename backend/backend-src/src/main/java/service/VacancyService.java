package service;

import dao.ResumeDao;
import dao.VacancyDao;
import dto.NewVacancyDto;
import entity.ResumeEntity;
import entity.VacancyEntity;
import entity.VacancyResponseEntity;
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

package ru.hh.nab.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.dao.VacancyDAO;
import ru.hh.nab.dto.ResponseVacancyDTO;
import ru.hh.nab.entity.Vacancy;

import java.util.List;

public class VacancyService {

    private final VacancyDAO vacancyDAO;

    public VacancyService(VacancyDAO vacancyDAO) {
        this.vacancyDAO = vacancyDAO;
    }

    @Transactional
    public Vacancy createVacancy(int companyId, String title,
                              String salary, String description, String contacts) {
        return vacancyDAO.addVacancy(companyId, title, salary, description, contacts);
    }

    @Transactional
    public List<ResponseVacancyDTO> getAllVacancy() {
        return vacancyDAO.getAllVacancy();
    }
}

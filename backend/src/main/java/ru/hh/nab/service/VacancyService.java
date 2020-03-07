package ru.hh.nab.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.dao.VacancyDAO;
import ru.hh.nab.dto.ResponseVacancyDTO;
import ru.hh.nab.entity.Company;
import ru.hh.nab.entity.Vacancy;

import java.time.LocalDate;
import java.util.List;

public class VacancyService {

    private final VacancyDAO vacancyDAO;

    private final CompanyService companyService;


    public VacancyService(VacancyDAO vacancyDAO, CompanyService companyService) {
        this.vacancyDAO = vacancyDAO;
        this.companyService = companyService;
    }

    @Transactional
    public Vacancy createVacancy(int companyId, String title,
                              String salary, String description, String contacts) {
        Company company = companyService.getCompanyById(companyId);
        return vacancyDAO.addVacancy(new Vacancy(
                company, title, Integer.valueOf(salary), description, contacts, true, LocalDate.now())
        );
    }

    @Transactional
    public List<ResponseVacancyDTO> getAllVacancy() {
        return vacancyDAO.getAllVacancy();
    }

    @Transactional
    public ResponseVacancyDTO getResponseVacancyById(int id) {
        return vacancyDAO.getResponseVacancyById(id);
    }

    @Transactional
    public Vacancy getVacancyById(Integer id) {
        return vacancyDAO.getVacancyById(id);
    }
}

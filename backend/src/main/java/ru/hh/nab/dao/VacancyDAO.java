package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.nab.dto.ResponseVacancyDTO;
import ru.hh.nab.entity.Company;
import ru.hh.nab.entity.Vacancy;
import ru.hh.nab.service.CompanyService;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VacancyDAO {

    private final SessionFactory sessionFactory;

    private final CompanyService companyService;

    public VacancyDAO(SessionFactory sessionFactory, CompanyService companyService) {
        this.sessionFactory = sessionFactory;
        this.companyService = companyService;
    }

    public Vacancy addVacancy(int companyId, String title,
                              String salary, String description, String contacts) {
        Company company = companyService.getCompanyById(companyId);
        Vacancy vacancy = new Vacancy(company, title, Integer.valueOf(salary),
                description, contacts, true, LocalDate.now());
        sessionFactory.getCurrentSession().save(vacancy);
        return vacancy;
    }

    public List<ResponseVacancyDTO> getAllVacancy() {
        return sessionFactory.getCurrentSession()
                .createQuery("select new ru.hh.nab.dto.ResponseVacancyDTO(" +
                                "vac.header, vac.lastUpdate, com.name, com.compId) " +
                                "from Vacancy vac " +
                                "join vac.company com where vac.active = true",
                        ResponseVacancyDTO.class).getResultList();
    }
}

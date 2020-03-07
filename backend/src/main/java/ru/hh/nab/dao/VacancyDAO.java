package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.nab.dto.ResponseVacancyDTO;
import ru.hh.nab.entity.Vacancy;

import java.util.List;

@Repository
public class VacancyDAO {

    private final SessionFactory sessionFactory;

    public VacancyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Vacancy addVacancy(Vacancy vacancy) {
        sessionFactory.getCurrentSession().save(vacancy);
        return vacancy;
    }

    public List<ResponseVacancyDTO> getAllVacancy() {
        return sessionFactory.getCurrentSession()
                .createQuery("select new ru.hh.nab.dto.ResponseVacancyDTO(" +
                                "vac.header, vac.lastUpdate, com.name, com.companyId) " +
                                "from Vacancy vac " +
                                "join vac.company com where vac.active = true",
                        ResponseVacancyDTO.class).getResultList();
    }

    public ResponseVacancyDTO getResponseVacancyById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("select new ru.hh.nab.dto.ResponseVacancyDTO(" +
                                "vac.header, vac.lastUpdate, com.name, com.companyId) " +
                                "from Vacancy vac " +
                                "join vac.company com where vac.vacancyId = :paramId and vac.active = true",
                        ResponseVacancyDTO.class).setParameter("paramId", id).getSingleResult();
    }

    public Vacancy getVacancyById(Integer id) {
        return sessionFactory.getCurrentSession().byId(Vacancy.class).load(id);
    }

}

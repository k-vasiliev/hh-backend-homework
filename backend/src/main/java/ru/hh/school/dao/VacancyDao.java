package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.hh.school.entity.Vacancy;

import java.util.List;

public class VacancyDao extends GenericDao {
    public VacancyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private Query eagerQuery() {
        return getSession().createQuery(
                "from Vacancy vacancy " +
                        "join fetch vacancy.area a " +
                        "join fetch vacancy.employer e " +
                        "join fetch vacancy.vacancyCounter vc " +
                        "join fetch e.area ea " +
                        "join fetch e.comment ec " +
                        "join fetch e.employerCounter ",
                Vacancy.class
        );
    }

    public List<Vacancy> getFavoritesWithPagination(Integer page, Integer perPage) {
        Query query =  eagerQuery();
        query.setFirstResult(perPage * page);
        query.setMaxResults(perPage);
        return query.getResultList();
    }

}

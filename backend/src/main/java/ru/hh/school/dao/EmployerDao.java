package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Popularity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployerDao extends GenericDao {

    public EmployerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Employer> getFavorites() {
        return getSession().createQuery("from Employer", Employer.class).getResultList();
    }

    @Transactional
    public List<Employer> getFavoritesWithPagination(Integer page, Integer perPage) {
        Query query =  getSession().createQuery("from Employer", Employer.class);
        query.setFirstResult(perPage * page);
        query.setMaxResults(perPage);
        List<Employer> employers = query.getResultList();
        employers.stream().forEach(
                employer -> {
                    Integer viewsCount = employer.getViewsCount();
                    employer.setViewsCount(employer.getViewsCount() + 1);
                    if (viewsCount == 49) {
                        employer.setPopularity(Popularity.POPULAR);
                    }
                }
        );
        return employers;
    }

}

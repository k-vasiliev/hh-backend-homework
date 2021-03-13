package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Popularity;

import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EmployerDao extends GenericDao {

    public EmployerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private Query eagerQuery() {
        return getSession().createQuery(
                "from Employer employer " +
                        "join fetch employer.area a " +
                        "join fetch employer.comment c " +
                        "join fetch employer.employerCounter ec ",
                Employer.class
        );
    }

    private Query<Employer> createEagerQueryById() {
        return getSession().createQuery(
                "from Employer employer " +
                        "join fetch employer.area a " +
                        "join fetch employer.comment c " +
                        "join fetch employer.employerCounter ec " +
                        "where employer.id = :id",
                Employer.class);
    }

    public Employer getEager(Integer id) {
        return createEagerQueryById()
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Employer> getFavoritesWithPagination(Integer page, Integer perPage) {
        Query query =  eagerQuery();
        query.setFirstResult(perPage * page);
        query.setMaxResults(perPage);
        return query.getResultList();
    }

    public Optional<Employer> getWithPessimisticLocking(Integer id) {
        try {
            return Optional.of(
                    createEagerQueryById()
                    .setParameter("id", id)
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}

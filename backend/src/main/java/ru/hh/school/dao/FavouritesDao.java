package ru.hh.school.dao;

import javax.inject.Inject;
import ru.hh.nab.hibernate.NabSessionFactoryBean;
import org.hibernate.Session;
import ru.hh.school.entity.*;

import java.util.List;

import org.springframework.context.annotation.Bean;

import javax.inject.Singleton;

import org.hibernate.SessionFactory;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.stream.Collectors;

@Singleton
public class FavouritesDao {
    private final SessionFactory sessionFactory;

    //@Inject
    public FavouritesDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public FavouriteVacancy create_vacancy(FavouriteVacancy vacancy) {
        sessionFactory.getCurrentSession().save(vacancy.getArea());
        sessionFactory.getCurrentSession().save(vacancy);
        return vacancy;
    }

    @Transactional
    public List<FavouriteVacancy> list_vacancies(int page, int per_page) {
        return sessionFactory.getCurrentSession().createQuery("from FavouriteVacancy")
                .setFirstResult(page * per_page) // equivalent to OFFSET
                .setMaxResults(per_page) // equivalent to LIMIT
                .list();
    }

    @Transactional
    public FavouriteVacancy get_vacancy(Integer id) {
        return (FavouriteVacancy) sessionFactory.getCurrentSession().get(FavouriteVacancy.class, id);
    }

    @Transactional
    public FavouriteVacancy edit_vacancy_comment(Integer vacancyId, String new_comment) {
        return sessionFactory.getCurrentSession().createQuery("update FavouriteVacancy vacancy set vacancy.comment = :comment where vacancy.id = :id", FavouriteVacancy.class)
                .setParameter("id", vacancyId)
                .setParameter("comment", new_comment)
                .getSingleResult();
    }

    @Transactional
    public FavouriteVacancy update_vacancy(FavouriteVacancy vacancy) {
        sessionFactory.getCurrentSession().merge(vacancy.getArea());
        sessionFactory.getCurrentSession().merge(vacancy);
        return vacancy;
    }

    @Transactional
    public void remove_vacancy(Integer vacancyId) {
        sessionFactory.getCurrentSession().createQuery("delete FavouriteVacancy vacancy where vacancy.id = :id")
                .setParameter("id", vacancyId)
                .executeUpdate();
    }

    @Transactional
    public FavouriteEmployer create_employer(FavouriteEmployer employer) {
        sessionFactory.getCurrentSession().save(employer.getArea());
        sessionFactory.getCurrentSession().save(employer);
        return employer;
    }

    @Transactional
    public List<FavouriteEmployer> list_employers(int page, int per_page) {
        return sessionFactory.getCurrentSession().createQuery("from FavouriteEmployer")
                .setFirstResult(page * per_page) // equivalent to OFFSET
                .setMaxResults(per_page) // equivalent to LIMIT
                .list();
    }

    @Transactional
    public FavouriteEmployer edit_employer_comment(Integer employerId, String new_comment) {
        return sessionFactory.getCurrentSession().createQuery("update FavouriteEmployer employer set employer.comment = :comment where employer.id = :id", FavouriteEmployer.class)
                .setParameter("id", employerId)
                .setParameter("comment", new_comment)
                .getSingleResult();
    }

    @Transactional
    public FavouriteEmployer update_employer(FavouriteEmployer employer) {
        sessionFactory.getCurrentSession().merge(employer.getArea());
        sessionFactory.getCurrentSession().merge(employer);
        return employer;
    }

    @Transactional
    public void remove_employer(Integer employerId) {
        sessionFactory.getCurrentSession().createQuery("delete FavouriteEmployer employer where employer.id = :id")
                .setParameter("id", employerId)
                .executeUpdate();
    }

    @Transactional
    public FavouriteEmployer get_employer(Integer id) {
        return (FavouriteEmployer) sessionFactory.getCurrentSession().get(FavouriteEmployer.class, id);
    }

    public FavouriteVacancy vacancyFromHHVacancy(ru.hh.school.hhapiclient.Vacancy hhVacancy) {
        FavouriteVacancy res = new FavouriteVacancy();
        res.setHHId(Integer.valueOf(hhVacancy.id));
        res.setEmployerId(Integer.valueOf(hhVacancy.employer.id));
        res.setEmployerName(hhVacancy.employer.name);
        Area resArea = new Area();
        resArea.setId(Integer.valueOf(hhVacancy.area.id));
        resArea.setName(hhVacancy.area.name);
        res.setArea(resArea);
        res.setName(hhVacancy.name);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        LocalDateTime dateTime = LocalDateTime.from(f.parse(hhVacancy.created_at));
        res.setCreatedAt(dateTime);
        res.setCreationTime(LocalDateTime.now());
        if (hhVacancy.salary == null) {
            res.setCompensationFrom(null);
            res.setCompensationTo(null);
            res.setCompensationGross(null);
        } else {
            res.setCompensationFrom(hhVacancy.salary.from);
            res.setCompensationTo(hhVacancy.salary.to);
            res.setCompensationGross(hhVacancy.salary.gross);
        }
        res.setViewsCount(0);
        return res;
    }

    public FavouriteEmployer employerFromHHEmployer(ru.hh.school.hhapiclient.Employer hhEmployer) {
        FavouriteEmployer res = new FavouriteEmployer();
        res.setHHId(Integer.valueOf(hhEmployer.id));
        Area resArea = new Area();
        resArea.setId(Integer.valueOf(hhEmployer.area.id));
        resArea.setName(hhEmployer.area.name);
        res.setArea(resArea);
        res.setName(hhEmployer.name);
        res.setDescription(hhEmployer.description);
        res.setViewsCount(0);
        res.setDateCreate(LocalDateTime.now());
        return res;
    }

    @Transactional
    public void incrementEmployerViews(Integer employerId) {
        // PostgreSQL can update atomically
        sessionFactory.getCurrentSession().createSQLQuery("UPDATE favourite_employers SET views_count = views_count + 1 where id = :id")
                .setParameter("id", employerId)
                .executeUpdate();
    }

    @Transactional
    public void incrementVacancyViews(Integer vacancyId) {
        // PostgreSQL can update atomically
        sessionFactory.getCurrentSession().createSQLQuery("UPDATE favourite_vacancies SET views_count = views_count + 1 where vacancy_id = :id")
                .setParameter("id", vacancyId)
                .executeUpdate();
    }
}

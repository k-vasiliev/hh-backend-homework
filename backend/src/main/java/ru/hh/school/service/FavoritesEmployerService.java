package ru.hh.school.service;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.FavoritesEmployerDao;
import ru.hh.school.dto.EmployerByIdDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.FavoritesEmployer;
import ru.hh.school.exception.ConstraintException;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.exception.InternalException;
import ru.hh.school.mapper.EmployerMapper;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Singleton
public class FavoritesEmployerService {

    private final EmployerService employerService;

    private final FavoritesEmployerDao favoritesEmployerDao;

    private final EmployerMapper employerMapper;


    public FavoritesEmployerService(EmployerService employerService, FavoritesEmployerDao favoritesEmployerDao, EmployerMapper employerMapper) {
        this.employerService = employerService;
        this.favoritesEmployerDao = favoritesEmployerDao;
        this.employerMapper = employerMapper;
    }

    @Transactional
    public FavoritesEmployer get(Integer employerId) {
        return favoritesEmployerDao.get(employerId).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public FavoritesEmployer saveOrUpdate(FavoritesEmployer employer) {
        return favoritesEmployerDao.saveOrUpdate(employer);
    }

    @Transactional
    public FavoritesEmployer update(Integer id, String comment) {
         return (id == null || comment == null) ? null : favoritesEmployerDao.update(
                 new FavoritesEmployer(id, comment, LocalDateTime.now()));
    }

    @Transactional
    public FavoritesEmployer create(Integer id, String comment) throws HhRequestException, ConstraintException {
        if (id == null) {
            throw new HhRequestException("employer id is empty");
        }
        // иду в апи хх, чтобы сохранить данные об работодателе
        // TODO можно ли сделать в отдельном не блокирующем потоке?
        EmployerByIdDto employerApiHh = employerService.get(String.valueOf(id));
        Employer employer = employerMapper.map(employerApiHh);
        employerService.saveOrUpdate(employer);


        //TODO очень плохо пахнет.
        // Как при создании отловить ошибку org.hibernate.exception.ConstraintViolationException ?
        Optional<FavoritesEmployer> favoritesEmployerOptional = favoritesEmployerDao.get(id);
        if (favoritesEmployerOptional.isPresent()) {
            throw new ConstraintException("Favorites employer already exist");
        }

        FavoritesEmployer favoritesEmployer = new FavoritesEmployer(id, comment);
        return favoritesEmployerDao.create(favoritesEmployer);
    }

    @Transactional
    public  List<FavoritesEmployer> getAll(Integer limit, Integer page) throws InternalException {
        final int defaultLimit = 100;
        final int defaultPage = 0;
        limit = limit == null ? defaultLimit : limit;
        page = page == null ? defaultPage : page;

        List<FavoritesEmployer> favoritesEmployers = null;
        try {
            favoritesEmployers = favoritesEmployerDao.getAll(limit, page);
        } catch (HibernateException e) {
            throw new InternalException(e.getMessage());
        }

        favoritesEmployers.stream()
                .peek(employer -> employer.setViewsCount(employer.getViewsCount() + 1))
                .forEach(favoritesEmployerDao::update);

        return favoritesEmployers;
    }

    @Transactional
    public void delete(Integer id) throws InternalException {
        if (id == null) {
            throw new InternalException("employer id is empty") ;
        }

        favoritesEmployerDao.delete(new FavoritesEmployer(id));
    }
}

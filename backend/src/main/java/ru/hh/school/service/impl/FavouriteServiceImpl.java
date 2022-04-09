package ru.hh.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.*;
import ru.hh.school.domain.*;
import ru.hh.school.exception.BadRequestException;
import ru.hh.school.exception.NotFoundException;
import ru.hh.school.resource.dto.HHEmployerResponseDto;
import ru.hh.school.resource.dto.HHVacancyResponseDto;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.FavouriteService;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@Service
@Transactional(readOnly = true)
public class FavouriteServiceImpl implements FavouriteService {
    private static final Logger logger = LoggerFactory.getLogger(FavouriteServiceImpl.class);
    private EmployerService employerService;
    private FavouriteRepository favouriteRepository;
    private EmployerRepository employerRepository;
    private VacancyRepository vacancyRepository;
    private AreaRepository areaRepository;


    @Autowired
    public FavouriteServiceImpl(EmployerService employerService, FavouriteRepository favouriteRepository, EmployerRepository employerRepository, VacancyRepository vacancyRepository, AreaRepository areaRepository) {
        this.employerService = employerService;
        this.favouriteRepository = favouriteRepository;
        this.employerRepository = employerRepository;
        this.vacancyRepository = vacancyRepository;
        this.areaRepository = areaRepository;
    }

    /**
     * Получение компаний от HH API
     * @return
     */
    @Override
    public List<Favourite> getEmployers(Integer page, Integer perPage) {
        return favouriteRepository.getAllByFavouriteType(FavouriteType.EMPLOYER);
    }

    @Override
    @Transactional
    public Favourite addEmployer(HHEmployerResponseDto hhEmployer, String comment) {
        System.out.println(0);
        Favourite favourite = favouriteRepository.getFavouriteByVacancyId(hhEmployer.getId());
        if (favourite != null) {
            throw new BadRequestException("Employer already existed");
        }
        System.out.println(1);
        Area area = areaRepository.getById(hhEmployer.getArea().getId());
        System.out.println(2);
        Employer employer = employerRepository.save(new Employer(hhEmployer.getName(), hhEmployer.getDescription(), area));
        System.out.println(3);
        return favouriteRepository.save(new Favourite(employer, hhEmployer.getId(), FavouriteType.EMPLOYER, comment, 0L));
    }

    @Override
    @Transactional
    public Employer refreshEmployer(HHEmployerResponseDto hhEmployer) {
        Employer employer = employerRepository.getById(hhEmployer.getId());
        if (employer == null) {
            return null;
        }
        if (!employer.getName().equals(hhEmployer.getName())) {
            employer.setName(hhEmployer.getName());
        }
        if (!employer.getDescription().equals(hhEmployer.getDescription())) {
            employer.setDescription(hhEmployer.getDescription());
        }
        if (!employer.getArea().getId().equals(hhEmployer.getArea().getId())) {
            Area area = areaRepository.getById(hhEmployer.getArea().getId());
            if (area == null) {
                throw new UnsupportedOperationException("Area not found");
            }
            employer.setArea(area);
        }
        return employerRepository.save(employer);
    }

    @Override
    @Transactional
    public void deleteEmployerById(Long employerId) {
        Favourite favourite = favouriteRepository.getFavouriteByEmployerId(employerId);
        if (favourite == null) {
            throw new NotFoundException("Favourite employer already existed");
        }
        favouriteRepository.delete(favourite);
    }

    @Override
    public List<Favourite> getVacancies(Integer page, Integer perPage) {
        return favouriteRepository.getAllByFavouriteType(FavouriteType.VACANCY);
    }

    @Override
    @Transactional
    public Favourite addVacancy(HHVacancyResponseDto hhVacancy, String comment) {
        Favourite favourite = favouriteRepository.getFavouriteByVacancyId(hhVacancy.getId());
        if (favourite != null) {
            throw new BadRequestException("Vacancy already existed");
        }
        Employer employer = employerRepository.getById(hhVacancy.getEmployer().getId());
        if (employer == null) {
            HHEmployerResponseDto hhEmployer = employerService.getHHEmployerById(hhVacancy.getEmployer().getId());
            Area area = areaRepository.getById(hhVacancy.getArea().getId());
            employer = employerRepository.save(new Employer(hhEmployer.getName(), hhEmployer.getDescription(), area));
        }
        Area area = areaRepository.getById(hhVacancy.getArea().getId());
        Vacancy vacancy = vacancyRepository.save(new Vacancy(hhVacancy.getName(), hhVacancy.getSalary(),
                null, // hhVacancy.getCreatedAt(),
                employer, area));
        return favouriteRepository.save(new Favourite(vacancy, hhVacancy.getId(), FavouriteType.VACANCY, comment, 0L));
    }

    @Override
    @Transactional
    public Vacancy refreshVacancy(HHVacancyResponseDto hhVacancy) {
        Vacancy vacancy = vacancyRepository.getById(hhVacancy.getId());
        if (vacancy == null) {
            return null;
        }
        if (!vacancy.getName().equals(hhVacancy.getName())) {
            vacancy.setName(hhVacancy.getName());
        }
        if (!vacancy.getSalary().equals(hhVacancy.getSalary())) {
            vacancy.setSalary(hhVacancy.getSalary());
        }
        if (!vacancy.getArea().getId().equals(hhVacancy.getArea().getId())) {
            Area area = areaRepository.getById(hhVacancy.getArea().getId());
            if (area == null) {
                throw new UnsupportedOperationException("Area not found");
            }
            vacancy.setArea(area);
        }
        if (!vacancy.getEmployer().getId().equals(hhVacancy.getEmployer().getId())) {
            Employer employer = employerRepository.getById(hhVacancy.getEmployer().getId());
            if (employer == null) {
                throw new UnsupportedOperationException("Employer not found");
            }
            vacancy.setEmployer(employer);
        }
        return vacancyRepository.save(vacancy);
    }

    @Override
    @Transactional
    public void deleteVacancyById(Long vacancyId) {
        Favourite favourite = favouriteRepository.getFavouriteByVacancyId(vacancyId);
        if (favourite == null) {
            throw new NotFoundException("Favourite vacancy already existed");
        }
        favouriteRepository.delete(favourite);
    }
}
package ru.hh.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.EmployerRepository;
import ru.hh.school.dao.FavouriteRepository;
import ru.hh.school.dao.VacancyRepository;
import ru.hh.school.domain.Employer;
import ru.hh.school.domain.Favourite;
import ru.hh.school.domain.FavouriteType;
import ru.hh.school.domain.Vacancy;
import ru.hh.school.exception.BadRequestException;
import ru.hh.school.exception.NotFoundException;
import ru.hh.school.resource.dto.HHEmployerResponseDto;
import ru.hh.school.resource.dto.HHVacancyResponseDto;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.FavouriteService;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
@Service
@Transactional(readOnly = true)
public class FavouriteServiceImpl implements FavouriteService {
    private static final Logger logger = LoggerFactory.getLogger(FavouriteServiceImpl.class);
    private final EmployerService employerService;
    private final FavouriteRepository favouriteRepository;
    private final EmployerRepository employerRepository;
    private final VacancyRepository vacancyRepository;


    @Autowired
    public FavouriteServiceImpl(EmployerService employerService, FavouriteRepository favouriteRepository,
                                EmployerRepository employerRepository, VacancyRepository vacancyRepository) {
        this.employerService = employerService;
        this.favouriteRepository = favouriteRepository;
        this.employerRepository = employerRepository;
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    @Transactional
    public void incrementViews(List<Favourite> favourites) {
        favourites.forEach(v -> {
            v.setViewsCount(v.getViewsCount() + 1);
            if (v.getVacancy() != null) {
                Optional.ofNullable(favouriteRepository.getFavouriteByEmployerId(v.getVacancy().getEmployer().getId()))
                        .ifPresent(vacancyFavouriteEmployer -> {
                            vacancyFavouriteEmployer.setViewsCount(vacancyFavouriteEmployer.getViewsCount() + 1);
                            favouriteRepository.save(vacancyFavouriteEmployer);
                        });
            }
            favouriteRepository.save(v);
        });
    }

    @Override
    public List<Favourite> getEmployers(Integer page, Integer perPage) {
        return favouriteRepository.getAllByFavouriteType(page, perPage, FavouriteType.EMPLOYER);
    }

    @Override
    public Long countEmployers() {
        return favouriteRepository.countAllByFavouriteType(FavouriteType.EMPLOYER);
    }

    @Override
    @Transactional
    public Favourite addEmployer(HHEmployerResponseDto hhEmployer, String comment) {
        Favourite favourite = favouriteRepository.getFavouriteByEmployerId(hhEmployer.getId());
        if (favourite != null) {
            throw new BadRequestException("Employer already existed");
        }
        Employer employer = employerRepository.save(new Employer(hhEmployer.getName(), hhEmployer.getDescription(), hhEmployer.getArea().getId()));
        return favouriteRepository.save(new Favourite(employer, hhEmployer.getId(), FavouriteType.EMPLOYER, comment));
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
        if (!employer.getAreaId().equals(hhEmployer.getArea().getId())) {
            employer.setAreaId(hhEmployer.getArea().getId());
        }
        return employerRepository.save(employer);
    }

    @Override
    @Transactional
    public Favourite updateFavouriteEmployer(Long employerId, String newComment) {
        Favourite favourite = favouriteRepository.getFavouriteByEmployerId(employerId);
        if (favourite == null) {
            throw new NotFoundException(String.format("Employer %d not found", employerId));
        }
        favourite.setComment(newComment);
        return favouriteRepository.save(favourite);
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
        return favouriteRepository.getAllByFavouriteType(page, perPage, FavouriteType.VACANCY);
    }

    @Override
    public Long countVacancies() {
        return favouriteRepository.countAllByFavouriteType(FavouriteType.VACANCY);
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
            employer = new Employer(hhEmployer.getName(), hhEmployer.getDescription(), hhVacancy.getArea().getId());
            favouriteRepository.save(new Favourite(employer, hhEmployer.getId(), FavouriteType.EMPLOYER, null)); // todo {strelchm}
//            employer = employerRepository.save(new Employer(hhEmployer.getName(), hhEmployer.getDescription(), hhVacancy.getArea().getId()));
        }
        Vacancy vacancy = vacancyRepository.save(new Vacancy(hhVacancy.getName(), hhVacancy.getSalary(),
                null, // hhVacancy.getCreatedAt(),
                employer, hhVacancy.getArea().getId()));
        return favouriteRepository.save(new Favourite(vacancy, hhVacancy.getId(), FavouriteType.VACANCY, comment));
    }

    @Override
    @Transactional
    public Favourite updateFavouriteVacancy(Long vacancyId, String newComment) {
        Favourite favourite = favouriteRepository.getFavouriteByVacancyId(vacancyId);
        if (favourite == null) {
            throw new NotFoundException(String.format("Vacancy %d not found", vacancyId));
        }
        favourite.setComment(newComment);
        return favouriteRepository.save(favourite);
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
        if (!vacancy.getAreaId().equals(hhVacancy.getArea().getId())) {
            vacancy.setAreaId(hhVacancy.getArea().getId());
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
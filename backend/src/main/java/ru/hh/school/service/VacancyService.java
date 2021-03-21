package ru.hh.school.service;

import org.springframework.stereotype.Service;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {
    private final VacancyDao vacancyDao;
    private final EmployerDao employerDao;
    private final HhApiService hhApiService;

    public VacancyService(VacancyDao vacancyDao, EmployerDao employerDao, HhApiService hhApiService) {
        this.vacancyDao = vacancyDao;
        this.employerDao = employerDao;
        this.hhApiService = hhApiService;
    }

    private Vacancy fromVacancyDtoToVacancy(VacancyDto vacancyDto, String comment, Integer views) {
        Vacancy vacancy = new Vacancy();
        Area area = new Area();

        Employer employer;
        Optional<Employer> optionalEmployer
                = employerDao.getEmployer(vacancyDto.getEmployerDto().getId());
        if (optionalEmployer.isEmpty()) {
            employer = new Employer();
            employer.setId(vacancyDto.getEmployerDto().getId());
            employer.setName(vacancyDto.getEmployerDto().getName());
            employer.setDateCreate(LocalDateTime.now());
            employer.setDescription(vacancyDto.getEmployerDto().getDescription());
            employer.setArea(area);
            employer.setViewsCount(0);
        } else employer = optionalEmployer.get();

        area.setId(vacancyDto.getAreaDto().getId());
        area.setName(vacancyDto.getAreaDto().getName());

        vacancy.setId(vacancyDto.getId());
        vacancy.setName(vacancyDto.getName());
        vacancy.setDateCreate(LocalDateTime.now());
        vacancy.setArea(area);
        if (vacancyDto.getSalaryDto() != null) {
            vacancy.setCompensationFrom(vacancyDto.getSalaryDto().getFrom());
            vacancy.setCompensationTo(vacancyDto.getSalaryDto().getTo());
            vacancy.setCurrency(vacancyDto.getSalaryDto().getCurrency());
            vacancy.setGross(vacancyDto.getSalaryDto().getGross());
        }
        vacancy.setCreatedAt(vacancyDto.getCreatedAt());
        vacancy.setEmployer(employer);
        vacancy.setViewsCount(views);
        vacancy.setComment(comment);

        return vacancy;
    }

    public List<Vacancy> getVacancyFromFavorites(Integer page, Integer perPage) {
        Optional<List<Vacancy>> vacancies = vacancyDao.getVacancies(page, perPage);

        vacancies.ifPresent(vacancyList -> vacancyList.forEach(employer ->
                vacancyDao.updateVacancyViews(employer.getId())));
        return vacancies.orElse(null);
    }

    public void saveVacancyToFavorites(Integer id, String comment) throws IOException, InterruptedException {
        VacancyDto vacancyDto = hhApiService.getVacancy(id);

        vacancyDao.saveVacancy(fromVacancyDtoToVacancy(vacancyDto, comment, 0));
    }

    public Vacancy editVacancyFavorites(Integer id, String comment) {
        Optional<Vacancy>vacancy = vacancyDao.getVacancy(id);

        return vacancy.map(value -> vacancyDao.editVacancy(id, comment).orElse(value)).orElse(null);
    }

    public Vacancy deleteVacancyFavorites(Integer id) {
        Optional<Vacancy> vacancy = vacancyDao.getVacancy(id);

        if (vacancy.isPresent()) {
            vacancyDao.deleteVacancy(vacancy.get());
            return vacancy.get();
        }

        return null;
    }

    public Vacancy refreshVacancyFavorites(Integer id) throws IOException, InterruptedException {
        Optional<Vacancy> vacancy = vacancyDao.getVacancy(id);

        if (vacancy.isPresent()) {
            Vacancy updatedVacancy = fromVacancyDtoToVacancy(hhApiService.getVacancy(id),
                    vacancy.get().getComment(),
                    vacancy.get().getViewsCount());
            vacancyDao.saveVacancy(updatedVacancy);
            return updatedVacancy;
        }
        return null;
    }
}

package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.FavoriteVacancyDto;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
public class VacancyService {

    private final ApiService apiService;
    private final VacancyDao vacancyDao;

    public VacancyService(ApiService apiService, VacancyDao vacancyDao) {
        this.apiService = apiService;
        this.vacancyDao = vacancyDao;
    }

    private Vacancy getVacancy(Integer id) {
        return vacancyDao.get(Vacancy.class, id).orElseThrow(NotFoundException::new);
    }

    //TODO
    public List<FavoriteVacancyDto> getFavorites(Integer page, Integer perPage) {
        return null;
    }

    public void addVacancyToFavorites(Integer vacancyId) {
        try {
            getVacancy(vacancyId);
            throw new BadRequestException("Bad request");
        } catch(NotFoundException e) {
            VacancyDto vacancyDto = apiService.fetchVacanciesFromApiById(vacancyId);

        }
    }

    public void deleteVacancy(Integer employerId) {
    }

    public void refresh(Integer employerId) {
    }


}

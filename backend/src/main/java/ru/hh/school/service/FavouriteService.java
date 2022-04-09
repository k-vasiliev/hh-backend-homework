package ru.hh.school.service;

import ru.hh.school.domain.Employer;
import ru.hh.school.domain.Favourite;
import ru.hh.school.domain.Vacancy;
import ru.hh.school.resource.dto.HHEmployerResponseDto;
import ru.hh.school.resource.dto.HHVacancyResponseDto;

import java.util.List;

public interface FavouriteService {
    void incrementViews(List<Favourite> favourites);

    List<Favourite> getEmployers(Integer page, Integer perPage);

    Long countEmployers();

    Favourite addEmployer(HHEmployerResponseDto hhEmployer, String comment);

    Favourite updateFavouriteEmployer(Long employerId, String newComment);

    Employer refreshEmployer(HHEmployerResponseDto hhEmployer);

    void deleteEmployerById(Long employerId);

    List<Favourite> getVacancies(Integer page, Integer perPage);

    Long countVacancies();

    Favourite addVacancy(HHVacancyResponseDto hhVacancy, String comment);

    Favourite updateFavouriteVacancy(Long vacancyId, String newComment);

    Vacancy refreshVacancy(HHVacancyResponseDto hhVacancy);

    void deleteVacancyById(Long vacancyId);
}
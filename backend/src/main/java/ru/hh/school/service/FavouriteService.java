package ru.hh.school.service;

import ru.hh.school.domain.Employer;
import ru.hh.school.domain.Favourite;
import ru.hh.school.domain.Vacancy;
import ru.hh.school.resource.dto.HHEmployerResponseDto;
import ru.hh.school.resource.dto.HHVacancyResponseDto;

import java.util.List;

public interface FavouriteService {
    List<Favourite> getEmployers(Integer page, Integer perPage);

    Favourite addEmployer(HHEmployerResponseDto hhEmployer, String comment);

    List<Favourite> getVacancies(Integer page, Integer perPage);

    Employer refreshEmployer(HHEmployerResponseDto hhEmployer);

    Vacancy refreshVacancy(HHVacancyResponseDto hhVacancy);

    void deleteEmployerById(Long employerId);

    void deleteVacancyById(Long vacancyId);

    Favourite addVacancy(HHVacancyResponseDto hhVacancy, String comment);
}
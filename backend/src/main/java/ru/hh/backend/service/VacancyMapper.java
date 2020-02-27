package ru.hh.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hh.backend.dao.CompanyDao;
import ru.hh.backend.dto.request.VacancyRequestDto;
import ru.hh.backend.dto.response.VacancyResponseDto;
import ru.hh.backend.entity.Vacancy;

@Service
public class VacancyMapper {

    CompanyDao companyDao;

    @Autowired
    public VacancyMapper(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public Vacancy map(VacancyRequestDto vacancyRequestDto) {
        return new Vacancy(vacancyRequestDto.getVacancyTitle(),
                vacancyRequestDto.getCompensation(),
                vacancyRequestDto.getDescription(),
                vacancyRequestDto.getContacts(),
                companyDao.get(vacancyRequestDto.getCompanyId()));
    }

    public VacancyResponseDto map(Vacancy vacancy) {
        return new VacancyResponseDto(vacancy.getId(),
                vacancy.getVacancyTitle(),
                vacancy.getCompany(),
                vacancy.getCompensation(),
                vacancy.getDescription(),
                vacancy.getContacts());
    }
}

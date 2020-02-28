package ru.hh.backend.homework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dao.CompanyDao;
import ru.hh.backend.homework.dao.NegotiationDao;
import ru.hh.backend.homework.dto.request.VacancyRequestDto;
import ru.hh.backend.homework.dto.response.VacancyResponseDto;
import ru.hh.backend.homework.entity.VacancyEntity;
import ru.hh.backend.homework.service.CompanyService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service
public class VacancyMapper {

    CompanyDao companyDao;

    @Autowired
    public VacancyMapper(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public VacancyEntity map(VacancyRequestDto vacancyRequestDto) {
        VacancyEntity vacancyEntity = new VacancyEntity();
        vacancyEntity.setVacancyTitle(vacancyRequestDto.getTitle());
        vacancyEntity.setCompensation(vacancyRequestDto.getSalary());
        vacancyEntity.setDescription(vacancyRequestDto.getDescription());
        vacancyEntity.setContacts(vacancyRequestDto.getContacts());
        vacancyEntity.setCompany(companyDao.get(vacancyRequestDto.getCompanyId()));

        return vacancyEntity;
    }

    public VacancyResponseDto map(VacancyEntity vacancy) {
        return new VacancyResponseDto(vacancy.getId(),
                vacancy.getVacancyTitle(),
                vacancy.getCompany(),
                vacancy.getCompensation(),
                vacancy.getDescription(),
                vacancy.getContacts(),
                vacancy.getCreationDate());
    }
}

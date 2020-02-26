package ru.hh.school.services;

import org.springframework.stereotype.Service;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.VacancyRequestDto;
import ru.hh.school.models.Company;
import ru.hh.school.models.Vacancy;

import javax.inject.Inject;
import java.util.List;

@Service
public class VacancyService {

    private VacancyDao vacancyDao;
    private CompanyDao companyDao;

    @Inject
    public VacancyService(VacancyDao vacancyDao, CompanyDao companyDao) {
        this.vacancyDao = vacancyDao;
        this.companyDao = companyDao;
    }

    public void saveNew(VacancyRequestDto vacancyDto) {
        Company company = companyDao.get(vacancyDto.getCompanyId());
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle(vacancyDto.getTitle());
//        vacancy.setCompany();
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setContacts(vacancyDto.getContacts());
        vacancy.setCompensation(vacancyDto.getCompensation());
        //TODO проверить, чтобы добавлялось время
        vacancyDao.create(vacancy);
    }

    public List<Vacancy> getAll() {
        return vacancyDao.getAll();
    }

}

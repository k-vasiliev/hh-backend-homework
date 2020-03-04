package ru.hh.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.back.dao.VacancyDao;
import ru.hh.back.dto.VacancyRequestDto;
import ru.hh.back.dto.VacancyResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    private VacancyDao vacancyDao;

    public VacancyService(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public List<VacancyResponseDto> getVacancy() {
        var vacancy = vacancyDao.getVacancy();
        return vacancy.stream().map(Mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public VacancyResponseDto getVacancy(Integer id) {
        var vacancy = vacancyDao.getVacancy(id);
        if (vacancy.isEmpty()){
            return null;
        }
        return Mapper.map(vacancy.get());
    }

    @Transactional
    public Integer createVacancy(VacancyRequestDto vacancyDto) {
        return vacancyDao.save(Mapper.map(vacancyDto));
    }


}

package ru.hh.backend.homework.mapper;

import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dto.VacancyDto;
import ru.hh.backend.homework.entity.VacancyEntity;

@Service
public class VacancyMapper {
    public VacancyDto map(VacancyEntity vacancyEntity) {
        return new VacancyDto(vacancyEntity.getId(),
                vacancyEntity.getCreationDate(),
                vacancyEntity.getModificationDate(),
                vacancyEntity.getTitle(),
                vacancyEntity.getSalary(),
                vacancyEntity.getDescription(),
                vacancyEntity.getCompanyEntity());
    }
}

package ru.hh.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hh.backend.dao.CompanyDao;
import ru.hh.backend.dto.request.VacancyRequestDto;
import ru.hh.backend.dto.response.VacancyInfoResponseDto;
import ru.hh.backend.dto.response.VacancyResponseDto;
import ru.hh.backend.model.Vacancy;


@Mapper(componentModel = "spring", uses = {CompanyDao.class, DateMapper.class})
public interface VacancyMapper {

    @Mapping(source = "companyId", target = "company")
    Vacancy map(VacancyRequestDto vacancyRequestDto);

    VacancyResponseDto map(Vacancy company);

    VacancyInfoResponseDto mapInfo(Vacancy vacancy);
}

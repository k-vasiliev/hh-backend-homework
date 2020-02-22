package ru.hh.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.dto.request.CompanyRequestDto;
import ru.hh.backend.dto.response.CompanyResponseDto;
import ru.hh.backend.model.Company;

@Mapper(componentModel = "spring", uses = {UserDao.class})
public interface CompanyMapper {

    @Mapping(source = "userId", target = "employer")
    Company map(CompanyRequestDto companyRequestDto);

    CompanyResponseDto map(Company company);
}

package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.CompanyRequestDto;
import ru.hh.backend.homework.dto.CompanyResponseDto;
import ru.hh.backend.homework.entity.CompanyEntity;

import javax.inject.Singleton;

@Singleton
public class CompanyMapper {
    public CompanyEntity map(CompanyRequestDto companyRequestDto) {
        CompanyEntity company = new CompanyEntity();
        company.setName(companyRequestDto.getName());
        company.setEmployer(companyRequestDto.getUserId());
        return company;
    }

    public CompanyResponseDto map(CompanyEntity companyEntity) {
        return new CompanyResponseDto(companyEntity.getCompanyId(), companyEntity.getName());
    }
}

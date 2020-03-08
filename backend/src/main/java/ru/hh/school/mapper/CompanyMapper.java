package ru.hh.school.mapper;



import ru.hh.school.dto.CompanyRequestDto;
import ru.hh.school.dto.CompanyResponseDto;
import ru.hh.school.entity.CompanyEntity;


import javax.inject.Singleton;

@Singleton
public class CompanyMapper {



    public CompanyEntity map(CompanyRequestDto companyRequestDto) {
        CompanyEntity company = new CompanyEntity();
        company.setName(companyRequestDto.getName());
        company.setEmployer(companyRequestDto.getUserEntity());
        return company;
    }

    public CompanyResponseDto map(CompanyEntity companyEntity) {
        return new CompanyResponseDto(companyEntity.getId(), companyEntity.getName());
    }
}
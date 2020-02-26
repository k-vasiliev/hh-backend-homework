package ru.hh.backend.homework.mapper;

import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dto.CompanyDto;
import ru.hh.backend.homework.entity.CompanyEntity;

@Service
public class CompanyMapper {
    public CompanyDto map(CompanyEntity companyEntity) {
        return new CompanyDto(companyEntity.getId(),
                companyEntity.getCreationDate(),
                companyEntity.getModificationDate(),
                companyEntity.getName(),
                companyEntity.getEmployer());
    }
}

package ru.hh.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.back.dao.CompanyDao;
import ru.hh.back.dto.CompanyDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    CompanyDao companyDao;
    public CompanyService(CompanyDao companyDao){
        this.companyDao = companyDao;
    }

    @Transactional
    public List<CompanyDto> getCompany(){
        var companies = companyDao.getCompany();
        var companiesDto = companies.stream().map(Mapper::map).collect(Collectors.toList());
        return companiesDto;
    }

    @Transactional
    public Integer saveCompany(CompanyDto company){
        return companyDao.save(Mapper.map(company));
    }

}

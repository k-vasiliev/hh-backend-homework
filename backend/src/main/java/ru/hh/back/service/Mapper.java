package ru.hh.back.service;

import ru.hh.back.dto.CompanyDto;
import ru.hh.back.dto.NegotiationCreateDto;
import ru.hh.back.dto.NegotiationGetDto;
import ru.hh.back.dto.ResumeCreateDto;
import ru.hh.back.dto.ResumeGetDto;
import ru.hh.back.dto.UserDto;
import ru.hh.back.dto.VacancyCreateDto;
import ru.hh.back.dto.VacancyGetDto;
import ru.hh.back.entity.CompanyEntity;
import ru.hh.back.entity.NegotiationEntity;
import ru.hh.back.entity.ResumeEntity;
import ru.hh.back.entity.UserEntity;
import ru.hh.back.entity.VacancyEntity;

public class Mapper {

    public static UserDto map(UserEntity userEntity) {
        return new UserDto(userEntity.getId(), userEntity.getName(), userEntity.getType());
    }

    public static CompanyDto map(CompanyEntity companyEntity){
        return new CompanyDto(companyEntity.getId(), companyEntity.getName());
    }

    public static CompanyEntity map(CompanyDto companyDto){
        var companyEntity = new CompanyEntity();
        companyEntity.setName(companyDto.getName());
        var user = new UserEntity();
        user.setId(companyDto.getUserId());
        companyEntity.setOwner(user);
        return companyEntity;
    }

    public static ResumeGetDto map(ResumeEntity resumeEntity) {
        var resumeGetDto = new ResumeGetDto();
        resumeGetDto.setId(resumeEntity.getId());
        resumeGetDto.setTitle(resumeEntity.getTitle());
        resumeGetDto.setDateCreate(resumeEntity.getCreationDate().toString());
        resumeGetDto.setApplicant(new ResumeGetDto.Applicant(resumeEntity.getUser().getName()));
        return resumeGetDto;
    }

    public static ResumeEntity map(ResumeCreateDto resumeCreateDto) {
        var resume = new ResumeEntity();
        resume.setTitle(resumeCreateDto.getTitle());
        resume.setContacts(resumeCreateDto.getContacts());
        resume.setWorkExperience(resumeCreateDto.getWorkExperience());
        var userEntity = new UserEntity();
        userEntity.setId(resumeCreateDto.getUserId());
        resume.setUser(userEntity);
        return resume;
    }

    public static VacancyGetDto map(VacancyEntity vacancy) {
        return new VacancyGetDto(vacancy.getTitle(),
                vacancy.getCompany().getName(),
                vacancy.getSalary(),
                vacancy.getDescription(),
                vacancy.getContacts()
        );
    }

    public static VacancyEntity map(VacancyCreateDto vacancy) {
        var vac = new VacancyEntity();
        vac.setContacts(vacancy.getContacts());
        vac.setDescription(vacancy.getDescription());
        vac.setTitle(vacancy.getTitle());
        vac.setSalary(vac.getSalary());
        var company = new CompanyEntity();
        company.setId(vacancy.getCompanyId());
        vac.setCompany(company);
        return vac;
    }

    public static NegotiationGetDto map(NegotiationEntity negotiationEntity){
        var negotiation = new NegotiationGetDto();
        negotiation.setResumeId(negotiationEntity.getResume().getId());
        negotiation.setVacancyId(negotiationEntity.getVacancy().getId());
        negotiation.setResume(map(negotiationEntity.getResume()));
        return negotiation;
    }

    public static NegotiationEntity map(NegotiationCreateDto negotiationCreateDto) {
        var entity = new NegotiationEntity();
        var resume = new ResumeEntity();
        resume.setId(negotiationCreateDto.getResumeId());
        var vacancy = new VacancyEntity();
        vacancy.setId(negotiationCreateDto.getVacancyId());
        entity.setResume(resume);
        entity.setVacancy(vacancy);
        return entity;
    }
}

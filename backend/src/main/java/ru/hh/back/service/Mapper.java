package ru.hh.back.service;

import ru.hh.back.dto.CompanyDto;
import ru.hh.back.dto.NegotiationRequestDto;
import ru.hh.back.dto.NegotiationResponseDto;
import ru.hh.back.dto.ResumeRequestDto;
import ru.hh.back.dto.ResumeResponseDto;
import ru.hh.back.dto.UserResponseDto;
import ru.hh.back.dto.VacancyRequestDto;
import ru.hh.back.dto.VacancyResponseDto;
import ru.hh.back.entity.CompanyEntity;
import ru.hh.back.entity.NegotiationEntity;
import ru.hh.back.entity.ResumeEntity;
import ru.hh.back.entity.UserEntity;
import ru.hh.back.entity.VacancyEntity;

import java.time.LocalDate;

public class Mapper {

    public static UserResponseDto map(UserEntity userEntity) {
        return new UserResponseDto(userEntity.getId(), userEntity.getName(), userEntity.getType().toString());
    }

    public static CompanyDto map(CompanyEntity companyEntity) {
        return new CompanyDto(companyEntity.getId(), companyEntity.getName(), companyEntity.getOwner().getId());
    }

    public static CompanyEntity map(CompanyDto companyDto) {
        var companyEntity = new CompanyEntity();
        companyEntity.setName(companyDto.getName());
        var user = new UserEntity();
        user.setId(companyDto.getUserId());
        companyEntity.setOwner(user);
        return companyEntity;
    }

    public static ResumeResponseDto map(ResumeEntity resumeEntity) {
        var resumeGetDto = new ResumeResponseDto();
        resumeGetDto.setId(resumeEntity.getId());
        resumeGetDto.setTitle(resumeEntity.getTitle());
        resumeGetDto.setDateCreate(resumeEntity.getCreationDate().toString());
        resumeGetDto.setApplicant(new ResumeResponseDto.Applicant(resumeEntity.getUser().getName()));
        return resumeGetDto;
    }

    public static ResumeEntity map(ResumeRequestDto resumeRequestDto) {
        var resume = new ResumeEntity();
        resume.setTitle(resumeRequestDto.getTitle());
        resume.setContacts(resumeRequestDto.getContacts());
        resume.setWorkExperience(resumeRequestDto.getWorkExperience());
        var userEntity = new UserEntity();
        userEntity.setId(resumeRequestDto.getUserId());
        resume.setUser(userEntity);
        resume.setCreationDate(LocalDate.now());
        return resume;
    }

    public static VacancyResponseDto map(VacancyEntity vacancy) {
        return new VacancyResponseDto(vacancy.getId(), vacancy.getTitle(),
                vacancy.getCompany().getName(),
                vacancy.getSalary(),
                vacancy.getDescription(),
                vacancy.getContacts(),
                vacancy.getCreationDate().toString()
        );
    }

    public static VacancyEntity map(VacancyRequestDto vacancy) {
        var vacancyEntity = new VacancyEntity();
        vacancyEntity.setContacts(vacancy.getContacts());
        vacancyEntity.setDescription(vacancy.getDescription());
        vacancyEntity.setTitle(vacancy.getTitle());
        vacancyEntity.setSalary(vacancy.getSalary());
        vacancyEntity.setCreationDate(LocalDate.now());
        var company = new CompanyEntity();
        company.setId(vacancy.getCompanyId());
        vacancyEntity.setCompany(company);
        return vacancyEntity;
    }

    public static NegotiationResponseDto map(NegotiationEntity negotiationEntity) {
        var negotiation = new NegotiationResponseDto();
        negotiation.setResumeId(negotiationEntity.getResume().getId());
        negotiation.setVacancyId(negotiationEntity.getVacancy().getId());
        negotiation.setResume(map(negotiationEntity.getResume()));
        return negotiation;
    }

    public static NegotiationEntity map(NegotiationRequestDto negotiationRequestDto) {
        var entity = new NegotiationEntity();
        var resume = new ResumeEntity();
        resume.setId(negotiationRequestDto.getResumeId());
        var vacancy = new VacancyEntity();
        vacancy.setId(negotiationRequestDto.getVacancyId());
        entity.setResume(resume);
        entity.setVacancy(vacancy);
        return entity;
    }
}

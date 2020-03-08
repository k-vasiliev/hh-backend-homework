package ru.hh.school.mapper;


import ru.hh.school.dto.ResumeRequestDto;
import ru.hh.school.dto.ResumeResponseDto;
import ru.hh.school.entity.ResumeEntity;

import javax.inject.Singleton;

@Singleton
public class ResumeMapper {
    public ResumeEntity map(ResumeRequestDto resumeRequestDto) {
        ResumeEntity resume = new ResumeEntity();
        resume.setHeader(resumeRequestDto.getHeader());
        resume.setExperience(resumeRequestDto.getExperience());
        resume.setContacts(resumeRequestDto.getContacts());
        resume.setApplicant(resumeRequestDto.getUserEntity());
        return resume;
    }

    public ResumeResponseDto map(ResumeEntity resumeEntity) {
        return new ResumeResponseDto(resumeEntity.getHeader(), resumeEntity.getId(), resumeEntity.getCreation_time());
    }
}
package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.ResumeRequestDto;
import ru.hh.backend.homework.dto.ResumeResponseDto;
import ru.hh.backend.homework.entity.ResumeEntity;

import javax.inject.Singleton;

@Singleton
public class ResumeMapper {
    public ResumeEntity map(ResumeRequestDto resumeRequestDto) {
        ResumeEntity resume = new ResumeEntity();
        resume.setTitle(resumeRequestDto.getTitle());
        resume.setApplicant(resumeRequestDto.getUserId());
        resume.setWorkExperience(resumeRequestDto.getWorkExperience());
        resume.setContacts(resumeRequestDto.getContacts());
        return resume;
    }

    public ResumeResponseDto map(ResumeEntity resumeEntity) {
        return new ResumeResponseDto(resumeEntity.getTitle(), resumeEntity.getResumeId(), resumeEntity.getCreationDate());
    }
}

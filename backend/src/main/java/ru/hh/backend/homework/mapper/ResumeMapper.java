package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.ResumeRequestDto;
import ru.hh.backend.homework.dto.ResumeResponseDto;
import ru.hh.backend.homework.entity.ResumeEntity;
import ru.hh.backend.homework.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ResumeMapper {
    private final UserService userService;

    @Inject
    public ResumeMapper(UserService userService) {
        this.userService = userService;
    }

    public ResumeEntity map(ResumeRequestDto resumeRequestDto) {
        ResumeEntity resume = new ResumeEntity();
        resume.setTitle(resumeRequestDto.getTitle());
        resume.setApplicant(userService.get(resumeRequestDto.getUserId()));
        resume.setWorkExperience(resumeRequestDto.getWorkExperience());
        resume.setContacts(resumeRequestDto.getContacts());
        return resume;
    }

    public ResumeResponseDto map(ResumeEntity resumeEntity) {
        return new ResumeResponseDto(resumeEntity.getTitle(), resumeEntity.getResumeId(), resumeEntity.getCreationDate());
    }
}

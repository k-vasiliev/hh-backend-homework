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
        return new ResumeEntity(resumeRequestDto.getTitle(),
                resumeRequestDto.getWorkExperience(),
                resumeRequestDto.getContacts(),
                userService.get(resumeRequestDto.getUserId()));
    }

    public ResumeResponseDto map(ResumeEntity resumeEntity) {
        return new ResumeResponseDto(resumeEntity.getTitle(), resumeEntity.getResumeId(), resumeEntity.getCreationDate());
    }
}

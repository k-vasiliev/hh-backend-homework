package ru.hh.backend.mapper;

import javassist.NotFoundException;
import ru.hh.backend.dto.ResumeDtoRequest;
import ru.hh.backend.dto.ResumeDtoResponse;
import ru.hh.backend.entity.Resume;
import ru.hh.backend.service.UserService;

import javax.inject.Singleton;
import java.time.LocalDate;

@Singleton
public class ResumeMapper {

    private UserService userService;
    private UserMapper userMapper;

    public ResumeMapper(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public ResumeDtoResponse map(Resume resume) {
        return new ResumeDtoResponse(
                resume.getResumeId(),
                userMapper.map(resume.getUser()),
                resume.getTitle(),
                resume.getCreationDate().toString());
    }

    public Resume map(ResumeDtoRequest resumeDtoRequest) throws NotFoundException {
        return new Resume(
                resumeDtoRequest.getTitle(),
                userService.getUser(resumeDtoRequest.getUserId()),
                resumeDtoRequest.getWorkExperience(),
                resumeDtoRequest.getContacts(), LocalDate.now());
    }
}

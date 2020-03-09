package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.request.ResumeRequestDto;
import ru.hh.school.dto.response.ResumeResponseDto;
import ru.hh.school.entity.Resume;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ResumeService {

    private final ResumeDao resumeDao;
    private final UserDao userDao;

    @Inject
    public ResumeService(ResumeDao resumeDao, UserDao userDao) {
        this.resumeDao = resumeDao;
        this.userDao = userDao;
    }

    @Transactional
    public void saveNew(ResumeRequestDto resumeDto) {
        User user = userDao.get(resumeDto.getUserId()).orElseThrow(NotFoundException::new);
        if (user.getUserType() != UserType.APPLICANT) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        resumeDao.create(mapToEntity(resumeDto, user));
    }

    @Transactional
    public List<ResumeResponseDto> getAll() {
        return resumeDao.getAll().stream()
                .map(ResumeService::mapToDto)
                .collect(Collectors.toList());
    }

    private Resume mapToEntity(ResumeRequestDto resumeDto, User user) {
        return new Resume(
                user,
                resumeDto.getTitle(),
                resumeDto.getWorkExperience(),
                resumeDto.getContacts());
    }

    protected static ResumeResponseDto mapToDto(Resume resume) {
        return new ResumeResponseDto(
                resume.getId(),
                resume.getTitle(),
                resume.getCreationDate().toString(),
                UserService.mapToDto(resume.getUser()));
    }
}

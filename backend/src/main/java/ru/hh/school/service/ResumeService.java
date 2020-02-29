package ru.hh.school.service;

import ru.hh.school.Utils;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.request.CreateResumeDto;
import ru.hh.school.dto.response.ResumeDto;
import ru.hh.school.entity.Resume;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ResumeService {

    private ResumeDao resumeDao;
    private UserDao userDao;

    @Inject
    public ResumeService(ResumeDao resumeDao, UserDao userDao) {
        this.resumeDao = resumeDao;
        this.userDao = userDao;
    }

    @Transactional
    public void createResume(CreateResumeDto dto) {
        User user = userDao.getUser(dto.getUserId());
        if (user == null || user.getType() != UserType.applicant) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Resume resume = new Resume();
        resume.setTitle(dto.getTitle());
        resume.setContacts(dto.getContacts());
        resume.setWorkExperience(dto.getWorkExperience());
        resume.setUser(user);
        resume.setCreatedAt(Instant.now());
        resume.setUpdatedAt(Instant.now());
        resumeDao.save(resume);
    }

    @Transactional
    public List<ResumeDto> getAllResumes() {
        return resumeDao.getAllResumes().stream()
            .map(ResumeService::convert).collect(Collectors.toList());
    }

    public static ResumeDto convert(Resume resume) {
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setId(resume.getResumeId());
        resumeDto.setTitle(resume.getTitle());
        resumeDto.setDateCreate(Utils.dateTimeFormatter.format(resume.getCreatedAt()));
        resumeDto.setApplicant(UserService.convert(resume.getUser()));
        return resumeDto;
    }
}

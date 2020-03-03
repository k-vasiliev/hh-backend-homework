package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.ResumeRequestDto;
import ru.hh.school.dto.ResumeResponseDto;
import ru.hh.school.entity.Resume;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
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
        User user = userDao.get(resumeDto.getUserId());
        if (user.getUserType() == UserType.APPLICANT) {
            Resume resume = new Resume();
            resume.setUser(user);
            resume.setTitle(resumeDto.getTitle());
            resume.setWorkExperience(resumeDto.getWorkExperience());
            resume.setContacts(resumeDto.getContacts());
            //TODO проверить, чтобы добавлялось время
            resumeDao.create(resume);
        }
//        else {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
    }

    @Transactional
    public Resume getBy(int resumeId) {
        return resumeDao.get(resumeId);
    }

    @Transactional
    public List<ResumeResponseDto> getAll() {
        return resumeDao.getAll().stream()
                .map(ResumeService::mapped)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Resume> getResumesForUserId(int userId) {
        return resumeDao.getByUserId(userId);
    }

    private static ResumeResponseDto mapped(Resume resume) {
        ResumeResponseDto resumeDto = new ResumeResponseDto();
        resumeDto.setId(resume.getId());
        resumeDto.setTitle(resume.getTitle());
        resumeDto.setApplicant(UserService.mapped(resume.getUser()));
        resumeDto.setDateCreate(resume.getCreationDate().toString());
        return resumeDto;
    }
}

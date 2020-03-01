package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.ResumeRequestDto;
import ru.hh.school.entity.Resume;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

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
    public List<Resume> getAll() {
        return resumeDao.getAll();
    }

    @Transactional
    public List<Resume> getResumesForUserId(int userId) {
        return resumeDao.getByUserId(userId);
    }
}

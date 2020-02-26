package ru.hh.school.services;

import org.springframework.stereotype.Service;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.ResumeRequestDto;
import ru.hh.school.models.Resume;
import ru.hh.school.models.User;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.List;

@Service
public class ResumeService {
    private final ResumeDao resumeDao;
    private final UserDao userDao;

    @Inject
    public ResumeService(ResumeDao resumeDao, UserDao userDao) {
        this.resumeDao = resumeDao;
        this.userDao = userDao;
    }

    public void saveNew(ResumeRequestDto resumeDto) {
        User user = userDao.get(resumeDto.getUserId());
        if (user.getUserType() == 0) {
            Resume resume = new Resume();
            resume.setUser(user);
            resume.setTitle(resumeDto.getTitle());
            resume.setWorkExperience(resumeDto.getWorkExperience());
            resume.setContacts(resumeDto.getContacts());
            //TODO проверить, чтобы добавлялось время
            resumeDao.create(resume);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public Resume getBy(int resumeId) {
        return resumeDao.get(resumeId);
    }

    public List<Resume> getAll() {
        return resumeDao.getAll();
    }

    public List<Resume> getResumesForUserId(int userId) {
        return resumeDao.getByUserId(userId);
    }
}

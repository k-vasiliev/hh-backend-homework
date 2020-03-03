package ru.hh.backend.service;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.dao.ResumeDao;
import ru.hh.backend.entity.Resume;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ResumeService {

    private ResumeDao resumeDao;

    public ResumeService(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Transactional
    public Resume getResume(Integer id) throws NotFoundException {
        Optional<Resume> resume = resumeDao.getResume(id);
        if (resume.isEmpty())
            throw new NotFoundException("User not found");
        else
            return resume.get();
    }

    @Transactional
    public List<Resume> getResumes() {
        return resumeDao.getResumes();
    }

    @Transactional
    public Integer save(Resume resume) {
        return resumeDao.save(resume);
    }
}

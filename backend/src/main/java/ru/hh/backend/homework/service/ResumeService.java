package ru.hh.backend.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.ResumeDao;
import ru.hh.backend.homework.entity.ResumeEntity;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {

    ResumeDao resumeDao;

    @Autowired
    public ResumeService(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Transactional
    public List<ResumeEntity> getAll() {
        return resumeDao.getAll();
    }

    @Transactional
    public ResumeEntity create(ResumeEntity resume) {
        return resumeDao.save(resume);
    }
}

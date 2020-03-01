package ru.hh.backend.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.ResumeDao;
import ru.hh.backend.homework.entity.ResumeEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ResumeService {
    private final ResumeDao resumeDao;

    @Inject
    public ResumeService(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Transactional
    public ResumeEntity save(ResumeEntity resumeEntity) {
        return resumeDao.save(resumeEntity);
    }

    @Transactional
    public ResumeEntity get(Integer id) {
        return resumeDao.get(id);
    }

    @Transactional
    public List<ResumeEntity> getAll() {
        return resumeDao.getAll();
    }
}

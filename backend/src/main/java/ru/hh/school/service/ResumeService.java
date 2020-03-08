package ru.hh.school.service;


import ru.hh.school.dao.ResumeDao;
import ru.hh.school.entity.ResumeEntity;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class ResumeService {
    private final ResumeDao resumeDao;

    public ResumeService(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Transactional
    public ResumeEntity create(ResumeEntity resumeEntity) {
        return resumeDao.create(resumeEntity);
    }

    @Transactional
    public ResumeEntity get(Long resumeId) {
        return resumeDao.get(resumeId);
    }

    @Transactional
    public List<ResumeEntity> getAll() {
        return resumeDao.getAll();
    }
}
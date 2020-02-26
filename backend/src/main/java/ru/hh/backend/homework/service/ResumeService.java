package ru.hh.backend.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.ResumeDao;
import ru.hh.backend.homework.entity.ResumeEntity;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {
    private final ResumeDao resumeDao;

    public ResumeService(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Transactional
    public ResumeEntity save(ResumeEntity resumeEntity) {
        return resumeDao.save(resumeEntity);
    }

    @Transactional
    public Optional<ResumeEntity> get(Integer id) {
        return resumeDao.get(id);
    }

    @Transactional
    public List<ResumeEntity> getAll() {
        return resumeDao.getAll();
    }
}

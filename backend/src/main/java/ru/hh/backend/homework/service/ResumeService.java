package ru.hh.backend.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.ResumeDao;
import ru.hh.backend.homework.entity.ResumeEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ResumeService {
    private final ResumeDao resumeDao;
    private static Logger logger = LoggerFactory.getLogger(ResumeService.class);

    @Inject
    public ResumeService(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Transactional
    public ResumeEntity save(ResumeEntity resumeEntity) {
        logger.info("Resume saved");
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

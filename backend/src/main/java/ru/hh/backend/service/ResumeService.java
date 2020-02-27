package ru.hh.backend.service;

import org.springframework.stereotype.Service;
import ru.hh.backend.dao.ResumeDao;
import ru.hh.backend.entity.Resume;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ResumeService {

  ResumeDao resumeDao;

  public ResumeService(ResumeDao resumeDao) {
    this.resumeDao = resumeDao;
  }

  @Transactional
  public List<Resume> getAll() {
    return resumeDao.getAll();
  }

  @Transactional
  public Resume create(Resume resume) {
    return resumeDao.create(resume);
  }

}

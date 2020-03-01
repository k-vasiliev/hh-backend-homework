package service;

import dao.CompanyDao;
import dao.ResumeDao;
import entity.ResumeEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ResumeService {
    private final ResumeDao resumeDao;

    public ResumeService(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Transactional
    public List<ResumeEntity> getResumes() {
        return resumeDao.getResumes();
    }
}

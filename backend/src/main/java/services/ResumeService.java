package services;

import dao.ResumeDao;
import models.Resume;
import org.hibernate.SessionFactory;
import utils.TransactionHelper;

import java.util.Optional;
import java.util.Set;

public class ResumeService {
    private final ResumeDao resumeDao;
    private final TransactionHelper th;

    public ResumeService(
            SessionFactory sessionFactory,
            ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
        this.th = new TransactionHelper(sessionFactory);
    }

    public void saveNew(Resume resume) {
        th.inTransaction(() -> resumeDao.saveNew(resume));
    }

    public Optional<Resume> getBy(int resumeId) {
        return th.inTransaction(() -> resumeDao.getBy(resumeId));
    }

    public Set<Resume> getActiveResumesForUserId(int userId) {
        return th.inTransaction(() -> resumeDao.getActiveResumesForUserId(userId));
    }
}

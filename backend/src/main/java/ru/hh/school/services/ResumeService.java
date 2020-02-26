package ru.hh.school.services;

import ru.hh.school.dao.ResumeDao;
import ru.hh.school.models.Resume;
import org.hibernate.SessionFactory;
import ru.hh.school.utils.TransactionHelper;

import java.util.List;
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
        th.inTransaction(() -> resumeDao.create(resume));
    }

    public Resume getBy(int resumeId) {
        return th.inTransaction(() -> resumeDao.get(resumeId));
    }

    public List<Resume> getResumesForUserId(int userId) {
        return th.inTransaction(() -> resumeDao.getByUserId(userId));
    }
}

package ru.hh.school.service;


import ru.hh.school.dao.NegotiationDao;
import ru.hh.school.entity.NegotiationEntity;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class NegotiationService {
    private final NegotiationDao negotiationDao;

    public NegotiationService(NegotiationDao negotiationDao) {
        this.negotiationDao = negotiationDao;
    }

    @Transactional
    public NegotiationEntity create(NegotiationEntity negotiationEntity) {
        return negotiationDao.create(negotiationEntity);
    }

    @Transactional
    public List<NegotiationEntity> getAllResumeByVacancyId(Long vacancyId) {
        return negotiationDao.getAllResumeByVacancyId(vacancyId);
    }
    @Transactional
    public List<NegotiationEntity> getAllVacancyByResumeId(Long resumeId) {
        return negotiationDao.getAllVacancyByResumeId(resumeId);
    }
}

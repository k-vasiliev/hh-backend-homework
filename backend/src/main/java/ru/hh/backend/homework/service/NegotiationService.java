package ru.hh.backend.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.NegotiationDao;
import ru.hh.backend.homework.entity.NegotiationEntity;

import java.util.List;

@Service
public class NegotiationService {
    private final NegotiationDao negotiationDao;

    public NegotiationService(NegotiationDao negotiationDao) {
        this.negotiationDao = negotiationDao;
    }

    @Transactional
    public NegotiationEntity save(NegotiationEntity negotiationEntity) {
        return negotiationDao.save(negotiationEntity);
    }

    @Transactional
    public List<NegotiationEntity> getAllByVacancy(Integer vacancyId) {
        return negotiationDao.getAllByVacancy(vacancyId);
    }
}

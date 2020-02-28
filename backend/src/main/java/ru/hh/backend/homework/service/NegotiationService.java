package ru.hh.backend.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.NegotiationDao;
import ru.hh.backend.homework.entity.NegotiationEntity;

import javax.inject.Singleton;
import java.util.List;

@Service
public class NegotiationService {
    NegotiationDao negotiationDao;

    @Autowired
    public NegotiationService(NegotiationDao negotiationDao) {
        this.negotiationDao = negotiationDao;
    }

    @Transactional
    public NegotiationEntity create(NegotiationEntity negotiation) {
        return negotiationDao.save(negotiation);
    }

    @Transactional
    public List<NegotiationEntity> getAllByVacancyId(Long id) {
        return negotiationDao.getAllByVacancy(id);
    }
}

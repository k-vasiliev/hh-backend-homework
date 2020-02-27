package ru.hh.backend.service;

import org.springframework.stereotype.Service;
import ru.hh.backend.dao.NegotiationDao;
import ru.hh.backend.entity.Negotiation;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NegotiationService {
    NegotiationDao negotiationDao;

    public NegotiationService(NegotiationDao negotiationDao) {
        this.negotiationDao = negotiationDao;
    }

    @Transactional
    public Negotiation create(Negotiation negotiation) {
        return negotiationDao.create(negotiation);
    }

    @Transactional
    public List<Negotiation> getAllByVacancyId(Long id) {
        return negotiationDao.getAllByVacancyId(id);
    }
}

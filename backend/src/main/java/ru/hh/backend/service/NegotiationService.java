package ru.hh.backend.service;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.dao.NegotiationDao;
import ru.hh.backend.entity.Negotiation;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class NegotiationService {

    private NegotiationDao negotiationDao;

    public NegotiationService(NegotiationDao negotiationDao) {
        this.negotiationDao = negotiationDao;
    }

    @Transactional
    public Negotiation getNegotiation(Integer id) throws NotFoundException {
        Optional<Negotiation> negotiation = negotiationDao.getNegotiation(id);
        if (negotiation.isEmpty())
            throw new NotFoundException("Negotiation not found");
        else
            return negotiation.get();
    }

    @Transactional
    public List<Negotiation> getVacancyNegotiations(Integer vacancyId) {
        return negotiationDao.getNegotiationsByVacancyId(vacancyId);
    }

    @Transactional
    public Integer save(Negotiation negotiation) {
        return negotiationDao.save(negotiation);
    }
}

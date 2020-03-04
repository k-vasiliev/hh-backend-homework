package ru.hh.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.back.dao.NegotiationDao;
import ru.hh.back.dto.NegotiationRequestDto;
import ru.hh.back.dto.NegotiationResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NegotiationService {
    private NegotiationDao negotiationDao;

    public NegotiationService(NegotiationDao negotiationDao) {
        this.negotiationDao = negotiationDao;
    }

    @Transactional
    public List<NegotiationResponseDto> getVacancyNegotiation(Integer vacancyId) {
        var negotiations = negotiationDao.getVacancyNegotiation(vacancyId);
        var negotiationsDto = negotiations.stream().map(Mapper::map).collect(Collectors.toList());
        return negotiationsDto;
    }

    @Transactional
    public Integer createNegotiation(NegotiationRequestDto negotiation) {
        return negotiationDao.save(Mapper.map(negotiation));
    }
}

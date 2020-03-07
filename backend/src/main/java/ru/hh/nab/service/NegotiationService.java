package ru.hh.nab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.dao.NegotiationDAO;
import ru.hh.nab.dto.ResponseNegotiationDTO;
import ru.hh.nab.dto.ResponseResumeDTO;
import ru.hh.nab.entity.Negotiation;
import ru.hh.nab.entity.Resume;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class NegotiationService {

    private final NegotiationDAO dao;

    private final ResumeService resumeService;

    private final VacancyService vacancyService;

    @Autowired
    public NegotiationService(NegotiationDAO dao, ResumeService resumeService, VacancyService vacancyService) {
        this.dao = dao;
        this.resumeService = resumeService;
        this.vacancyService = vacancyService;
    }

    @Transactional
    public ResponseNegotiationDTO getNegotiationById(Integer id) {
        return map(dao.getNegotiationById(id));
    }

    @Transactional
    public Negotiation createNegotiation(Integer resumeId, Integer vacancyId) {
        return dao.createNegotiation(
                new Negotiation(
                        resumeService.getResumeById(resumeId), vacancyService.getVacancyById(vacancyId),
                        LocalDate.now(), true
                )
        );
    }

    @Transactional
    public List<ResponseNegotiationDTO> getAllNegotiations() {
        return dao.getAllNegotiations().stream().map(this::map).collect(Collectors.toList());
    }

    private ResponseNegotiationDTO map(Negotiation negotiation) {
        Resume resume = negotiation.getResume();
        ResponseResumeDTO responseResume = resumeService.getResponseResumeById(resume.getResumeId());
        return new ResponseNegotiationDTO(negotiation.getNegotiationId(), resume.getResumeId(),
                negotiation.getVacancy().getVacancyId(), responseResume
        );
    }
}

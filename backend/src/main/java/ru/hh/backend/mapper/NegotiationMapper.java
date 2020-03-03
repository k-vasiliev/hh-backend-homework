package ru.hh.backend.mapper;

import javassist.NotFoundException;
import ru.hh.backend.dto.NegotiationDtoRequest;
import ru.hh.backend.dto.NegotiationDtoResponse;
import ru.hh.backend.entity.Negotiation;
import ru.hh.backend.service.NegotiationService;
import ru.hh.backend.service.ResumeService;
import ru.hh.backend.service.VacancyService;

import javax.inject.Singleton;
import java.time.LocalDate;

@Singleton
public class NegotiationMapper {

    private ResumeService resumeService;
    private VacancyService vacancyService;
    private ResumeMapper resumeMapper;

    public NegotiationMapper(ResumeService resumeService, VacancyService vacancyService, ResumeMapper resumeMapper) {
        this.resumeService = resumeService;
        this.vacancyService = vacancyService;
        this.resumeMapper = resumeMapper;
    }

    public NegotiationDtoResponse map(Negotiation negotiation) {
        return new NegotiationDtoResponse(
                negotiation.getNegotiationId(),
                negotiation.getVacancy().getVacancyId(),
                resumeMapper.map(negotiation.getResume()));
    }

    public Negotiation map(NegotiationDtoRequest negotiationDtoRequest) throws NotFoundException {
        return new Negotiation(
                vacancyService.getVacancy(negotiationDtoRequest.getVacancyId()),
                resumeService.getResume(negotiationDtoRequest.getResumeId()),
                LocalDate.now());
    }
}

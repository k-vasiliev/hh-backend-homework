package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.*;
import ru.hh.school.dto.request.NegotiationRequestDto;
import ru.hh.school.dto.response.NegotiationResponseDto;
import ru.hh.school.entity.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class NegotiationService {

    private NegotiationDao negotiationDao;
    private ResumeDao resumeDao;
    private VacancyDao vacancyDao;

    @Inject
    public NegotiationService(NegotiationDao negotiationDao, ResumeDao resumeDao, VacancyDao vacancyDao) {
        this.negotiationDao = negotiationDao;
        this.resumeDao = resumeDao;
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public void saveNew(NegotiationRequestDto negotiationDto) {
        Resume resume = resumeDao.get(negotiationDto.getResumeId())
                .orElseThrow(NotFoundException::new);
        Vacancy vacancy = vacancyDao.get(negotiationDto.getVacancyId())
                .orElseThrow(NotFoundException::new);
        negotiationDao.create(mapToEntity(resume, vacancy));
    }

    @Transactional
    public List<NegotiationResponseDto> getNegotiationsDtoByVacancyId(Integer vacancyId) {
        return negotiationDao.getByVacancyId(vacancyId).stream()
                .map(NegotiationService::mapToDto)
                .collect(Collectors.toList());
    }

    private Negotiation mapToEntity(Resume resume, Vacancy vacancy) {
        Negotiation negotiation = new Negotiation();
        negotiation.setResume(resume);
        negotiation.setVacancy(vacancy);
        return negotiation;
    }

    private static NegotiationResponseDto mapToDto(Negotiation negotiation) {
        NegotiationResponseDto negotiationDto = new NegotiationResponseDto();
        negotiationDto.setId(negotiation.getId());
        negotiationDto.setResume(ResumeService.mapToDto(negotiation.getResume()));
        return negotiationDto;
    }
}

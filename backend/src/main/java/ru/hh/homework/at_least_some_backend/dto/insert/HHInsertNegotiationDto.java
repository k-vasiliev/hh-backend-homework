package ru.hh.homework.at_least_some_backend.dto.insert;

import ru.hh.homework.at_least_some_backend.entity.HHNegotiation;

public class HHInsertNegotiationDto extends InsertDto<HHNegotiation>
{
    private Long vacancyId;
    private Long resumeId;

    public Long getVacancyId() { return vacancyId; }
    public void setVacancyId(Long vacancyId) { this.vacancyId = vacancyId; }

    public Long getResumeId() { return resumeId; }
    public void setResumeId(Long resumeId) { this.resumeId = resumeId; }
}

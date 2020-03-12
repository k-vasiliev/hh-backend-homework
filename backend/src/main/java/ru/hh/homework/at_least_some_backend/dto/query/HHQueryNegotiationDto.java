package ru.hh.homework.at_least_some_backend.dto.query;

import ru.hh.homework.at_least_some_backend.entity.HHNegotiation;

public class HHQueryNegotiationDto
{
    private Long id;
    private HHQueryResumeDto resume;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public HHQueryResumeDto getResume() { return resume; }
    public void setResume(HHQueryResumeDto resume) { this.resume = resume; }

    public static HHQueryNegotiationDto fromEntity(HHNegotiation entity)
    {
        if (entity == null) return null;

        var dto = new HHQueryNegotiationDto();

        dto.setId(entity.getId());
        dto.setResume(HHQueryResumeDto.fromEntity(entity.getResume()));

        return dto;
    }
}

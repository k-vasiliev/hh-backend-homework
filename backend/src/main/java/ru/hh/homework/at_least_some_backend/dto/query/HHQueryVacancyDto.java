package ru.hh.homework.at_least_some_backend.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.homework.at_least_some_backend.Utils;
import ru.hh.homework.at_least_some_backend.entity.HHVacancy;

public class HHQueryVacancyDto
{
    private String title;
    private HHQueryCompanyDto company;
    @JsonProperty("dateCreate")
    private String createdAt;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public HHQueryCompanyDto getCompany() { return company; }
    public void setCompany(HHQueryCompanyDto company) { this.company = company; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public static HHQueryVacancyDto fromEntity(HHVacancy entity)
    {
        if (entity == null) return null;

        var dto = new HHQueryVacancyDto();

        dto.setTitle(entity.getTitle());
        dto.setCompany(HHQueryCompanyDto.fromEntity(entity.getCompany()));

        var createdAt = entity.getCreatedAt();
        if (createdAt != null)
            dto.setCreatedAt(
                    Utils.formatDateTime(Utils.toMoscowDateTime(createdAt))
            );

        return dto;
    }
}

package ru.hh.homework.at_least_some_backend.dto.insert;

import ru.hh.homework.at_least_some_backend.entity.HHCompany;

public class HHInsertCompanyDto extends InsertDto<HHCompany>
{
    private String name;
    private Long userId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

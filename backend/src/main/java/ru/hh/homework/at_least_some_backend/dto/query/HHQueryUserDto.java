package ru.hh.homework.at_least_some_backend.dto.query;

import ru.hh.homework.at_least_some_backend.entity.HHUser;

public class HHQueryUserDto
{
    private Long id;
    private String name;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public static HHQueryUserDto fromEntity(HHUser entity)
    {
        if (entity == null) return null;

        var responseDto = new HHQueryUserDto();

        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());

        return responseDto;
    }
}

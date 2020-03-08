package ru.hh.homework.at_least_some_backend.dto;

import ru.hh.homework.at_least_some_backend.entity.HHUser;

public class HHUserDto
{
    private Long id;
    private String name;
    private HHUser.UserType type;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public HHUser.UserType getType() { return type; }
    public void setType(HHUser.UserType type) { this.type = type; }

    public static HHUserDto entityToResponseDto(HHUser entity)
    {
        if (entity == null) return null;

        var responseDto = new HHUserDto();

        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        responseDto.setType(entity.getType());

        return responseDto;
    }
}

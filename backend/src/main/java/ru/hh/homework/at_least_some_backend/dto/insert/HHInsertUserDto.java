package ru.hh.homework.at_least_some_backend.dto.insert;

import ru.hh.homework.at_least_some_backend.entity.HHUser;

public class HHInsertUserDto
{
    private String name;
    private HHUser.UserType type;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public HHUser.UserType getType() { return type; }
    public void setType(HHUser.UserType type) { this.type = type; }

    public static HHUser toNewEntity(HHInsertUserDto dto)
    {
        if (dto == null) return null;

        var entity = new HHUser();

        entity.setName(dto.getName());
        entity.setType(dto.getType());

        return entity;
    }
}

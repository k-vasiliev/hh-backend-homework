package ru.hh.homework.at_least_some_backend.dto.insert;

import ru.hh.homework.at_least_some_backend.entity.HHUser;

public class HHInsertUserDto extends InsertDto<HHUser>
{
    private String name;
    private HHUser.UserType type;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public HHUser.UserType getType() { return type; }
    public void setType(HHUser.UserType type) { this.type = type; }
}

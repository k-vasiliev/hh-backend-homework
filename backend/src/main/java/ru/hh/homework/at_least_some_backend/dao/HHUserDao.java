package ru.hh.homework.at_least_some_backend.dao;

import javax.inject.Singleton;

import ru.hh.homework.at_least_some_backend.entity.HHUser;

@Singleton
public class HHUserDao extends CommonDao<HHUser>
{
    public HHUserDao() { super(HHUser.class); }
}

package ru.hh.homework.at_least_some_backend.dao;

import ru.hh.homework.at_least_some_backend.entity.HHResume;

import javax.inject.Singleton;

@Singleton
public class HHResumeDao extends CommonDao<HHResume>
{
    public HHResumeDao() { super(HHResume.class); }
}

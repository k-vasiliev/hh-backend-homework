package ru.hh.homework.at_least_some_backend.dao;

import ru.hh.homework.at_least_some_backend.entity.HHVacancy;

import javax.inject.Singleton;

@Singleton
public class HHVacancyDao extends CommonDao<HHVacancy>
{
    public HHVacancyDao() { super(HHVacancy.class); }
}

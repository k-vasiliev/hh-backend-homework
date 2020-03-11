package ru.hh.homework.at_least_some_backend.dao;

import ru.hh.homework.at_least_some_backend.entity.HHCompany;

import javax.inject.Singleton;

@Singleton
public class HHCompanyDao extends CommonDao<HHCompany>
{
    public HHCompanyDao() { super(HHCompany.class); }
}

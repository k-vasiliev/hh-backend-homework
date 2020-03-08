package ru.hh.homework.at_least_some_backend.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import ru.hh.homework.at_least_some_backend.dao.HHUserDao;
import ru.hh.homework.at_least_some_backend.entity.HHUser;

@Singleton
public class HHUserService
{
    @Inject
    private HHUserDao dao;

    @Transactional
    public HHUser queryById(Long id)
    {
        return dao.queryEntityById(id);
    }
}
